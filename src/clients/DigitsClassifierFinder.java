package clients;

import classifiers.common.ClassifierServerInterface;
import classifiers.services.*;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Instances;

import java.util.HashSet;

//import weka.classifiers.functions.LibSVM;

public class DigitsClassifierFinder {

    public static Instances[] split(Instances instances, int percent){
        int size1 = (int) Math.round(instances.numInstances() * percent / 100);
        int size2 = instances.numInstances() - size1;
        Instances part1 = new Instances(instances, 0, size1);
        Instances part2 = new Instances(instances, size1, size2);
        return new Instances[] { part1, part2} ;
    }

    public static ClassifierServerInterface findBestClassifier(Instances instances) throws Exception {

        // split
        Instances[] split = split(instances, 70) ;
        Instances train = split[0] ;
        Instances cross = split[1];

        Evaluation eval ;
        double bestKappa  ;
        Classifier bestClassifier ;

        String serverName = "localhost" ;

        HashSet<ClassifierThread> threads = new HashSet<ClassifierThread>() ;
        threads.add(new ClassifierThread(J48ClassifierService.CLASSIFIER_CLASS, serverName, J48ClassifierService.PORT, train, cross)) ;
        threads.add(new ClassifierThread(NaiveBayesClassifierService.CLASSIFIER_CLASS, serverName, NaiveBayesClassifierService.PORT, train, cross)) ;
        threads.add(new ClassifierThread(SMOClassifierService.CLASSIFIER_CLASS, serverName, SMOClassifierService.PORT, train, cross)) ;

        String[] options ;
        options = weka.core.Utils.splitOptions("-G 0.1 -C 1") ;
        threads.add(new ClassifierThread(LibSVMClassifierService.CLASSIFIER_CLASS, options, serverName, LibSVMClassifierService.PORT, train, cross)) ;

        options = weka.core.Utils.splitOptions("-L 0.01 -M 0.9 -N 100 -S 1 -H 25") ;
        threads.add(new ClassifierThread(MLPClassifierService.CLASSIFIER_CLASS, options, serverName, MLPClassifierService.PORT, train, cross)) ;

        for (Thread t : threads) t.start();

        for (Thread t: threads) t.join();
        ClassifierThread best = null ;
        for (ClassifierThread t: threads){
            if(best == null) { best = t ; continue; }
            if(t.getEval().kappa() > best.getEval().kappa()) best = t ;
        }


        System.out.println();
        System.out.println("--------------------------");
        System.out.println();
        System.out.println("Best classifierServer : " + best.getClassifierClass().getSimpleName()) ;

        return best.getClassifierServer() ;
    }

    public static void main(String[] args) throws Exception {

        Instances dataset = DigitsLoader.loadDigitsData() ;

        Instances[] split = split(dataset, 80) ;
        dataset = split[0];
        Instances test = split[1] ;

        // test and eval classifiers
        ClassifierServerInterface bestClassifier = findBestClassifier(dataset);

        // classify an instance
        double result = bestClassifier.classifyInstance(test.firstInstance());
        System.out.println();
        System.out.println("--- Classify an instance ---");
        System.out.println();
        System.out.println("Real value : " + test.classAttribute().value((int)test.firstInstance().classValue())) ;
        System.out.println("Predicted : " + test.classAttribute().value((int)result));

    }

}
