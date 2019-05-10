package accu;

import accumulator.Accumulator;
import accumulator.AccumulatorHelper;
import accumulator.AccumulatorHolder;
import org.omg.CORBA.ORB;

import java.io.BufferedReader;
import java.io.FileReader;

public class AccumulatorClient {

    public static void main(String[] args) throws Exception {

        ORB orb = ORB.init(args,null);

        BufferedReader br = new BufferedReader(new FileReader("ior.txt"));

        String ior = br.readLine();
        br.close();

        org.omg.CORBA.Object o = orb.string_to_object(ior);

        Accumulator accumulator = AccumulatorHelper.narrow(o);
        accumulator.add(5);


    }
}
