import java.util.*;
import java.io.*;
import java.math.*;


abstract class Module{
    public abstract void decision(Robot robot);
}

class StartPoint extends Module{

    @Override
    public void decision(Robot robot)
    {
        robot.GoTo("DIAGNOSIS");


    }
}
class Diagnosis extends Module{

    @Override
    public void decision(Robot robot)
    {
        robot.GoTo("MOLECULES");
    }
}
class Molecules extends Module{

    @Override
    public void decision(Robot robot)
    {
        robot.GoTo("LABORATORY");
    }
}
class Laboratory extends Module{

    @Override
    public void decision(Robot robot)
    {
        robot.GoTo("DIAGNOSIS");
    }
}


class Sample implements Comparable<Sample> {
    public int sampleId;
    public int carriedBy;
    public int health;
    public int rank;
    public String expertiseGain;
    public int costA;
    public int costB;
    public int costC;
    public int costD;
    public int costE;
    public int totalCost;
    public float effectiveCost;

    public Sample(int _sampleId, int _carriedBy, String _expertiseGain, int _health, int _rank, int _costA, int _costB, int _costC, int _costD, int _costE )
    {
        this.sampleId = _sampleId;
        this.carriedBy = _carriedBy;
        this.expertiseGain = _expertiseGain;
        this.costA = _costA;
        this.costB = _costB;
        this.costC = _costC;
        this.costD = _costD;
        this.costE = _costE;
        this.health = _health;
        this.rank = _rank;
        this.totalCost = _costA + _costB + _costC + _costD + _costE;
        this.effectiveCost = (float) this.health / this.totalCost;
    }

    public int getCarriedBy() {
        return carriedBy;
    }
    public void setCarriedBy(int _carriedBy){
        this.carriedBy = _carriedBy;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int _health) {
        this.health = _health;
    }

    public int getSampleId() {
        return sampleId;
    }

    public void setSampleId(int _sampleId) {
        this.sampleId = _sampleId;
    }

    @Override
    public String toString() {
        return "carriedBy" + carriedBy + "sampleid = " + sampleId +"- totalcost= "+ totalCost + "- health= " + health + "-- effectiveCost = " + this.effectiveCost;
    }


    @Override
    public int compareTo(Sample o) {
        return  Float.compare(effectiveCost,o.effectiveCost);
    }
}

class Robot {
    public int[] storage;
    public int[] expertise;
    public Module target;
    public int score;
    Sample sample ;

    public Robot (){

    }

    public Robot (int[] _storage, int[] _expertise, Module _target, int _score){
        this.storage = _storage;
        this.expertise = _expertise;
        this.target = _target;
        this.score = _score;
    }

    public void setTarget(Module _target) {
        this.target = _target;
    }

    public Module getTarget() {
        return target ;
    }

    public int[] getStorage() {
        return storage;
    }

    public void setStorage(int[] _storage){
        this.storage = _storage;
    }

    public int getScore(){
        return score;
    }

    public void setScore(int health){
        this.score += health;
    }

    public void GoTo(String module)
    {
        System.out.println("GOTO " + module);
    }


    public void Take(Sample s)
    {
        this.sample = s;
        System.out.println("CONNECT " + s.sampleId );
    }

    public void TakeMolecules()
    {
        for (int i = sample.costA; 0 < i; i--) {
            System.out.println("CONNECT " + "A" );
        }

        for (int i = sample.costB; 0 < i; i--) {
            System.out.println("CONNECT " + "B" );
        }

        for (int i = sample.costC; 0 < i; i--) {
            System.out.println("CONNECT " + "C" );
        }

        for (int i = sample.costD; 0 < i; i--) {
            System.out.println("CONNECT " + "D" );
        }

        for (int i = sample.costE; 0 < i; i--) {
            System.out.println("CONNECT " + "E" );
        }
    }

    public void GiveMolecules()
    {
        System.out.println("CONNECT " + sample.sampleId);
    }

    public Sample TakeBestSample( List<Sample> samples)
    {
        for (Sample s : samples) {
            if(s.carriedBy == -1 )
            {
                return s;
            }

        }
        return null;
    }

    public void Update()
    {
        // target.decision(this);
    }


}

class Project {
    public int[] expertise;

    public Project(int[] _expertise)
    {
        expertise = _expertise;
    }
}

/**
 * Bring data on patient samples from the diagnosis machine to the laboratory with enough molecules to produce medicine!
 **/


class Player {
    public static ArrayList<Sample> samples;
    public static int samplesTaken;
    public static int[] available;
    public static List<Project> projects;
    public static List<Robot> robots;


    public static void main(String args[]) {
        samples = new ArrayList<Sample>();
        robots = new ArrayList<Robot>();
        projects = new ArrayList<Project>();

        Scanner in = new Scanner(System.in);
        int projectCount = in.nextInt();
        for (int i = 0; i < projectCount; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            int c = in.nextInt();
            int d = in.nextInt();
            int e = in.nextInt();

            projects.add(new Project(new int[]{a,b,c,d,e}));

        }

        // game loop
        while (true) {


            for (int i = 0; i < 2; i++) {
                String target = in.next();
                int eta = in.nextInt();
                int score = in.nextInt();
                int storageA = in.nextInt();
                int storageB = in.nextInt();
                int storageC = in.nextInt();
                int storageD = in.nextInt();
                int storageE = in.nextInt();
                int expertiseA = in.nextInt();
                int expertiseB = in.nextInt();
                int expertiseC = in.nextInt();
                int expertiseD = in.nextInt();
                int expertiseE = in.nextInt();
                Module moduleTarget = null;
                switch (target)
                {
                    case "START_POS":
                        moduleTarget = new StartPoint();
                        break;
                    case "DIAGNOSIS":
                        moduleTarget = new Diagnosis();
                        break;
                    case "MOLECULES":
                        moduleTarget = new Molecules();
                        break;
                    case "LABORATORY":
                        moduleTarget = new Laboratory();
                        break;
                    default:
                        break;
                }
                robots.add(new Robot(new int[] {storageA, storageB, storageC, storageD, storageE}, new int[] {expertiseA, expertiseB, expertiseC, expertiseD, expertiseE}, moduleTarget, score));

            }
            int availableA = in.nextInt();
            int availableB = in.nextInt();
            int availableC = in.nextInt();
            int availableD = in.nextInt();
            int availableE = in.nextInt();

            available = new int[] {availableA, availableB, availableC, availableD, availableE};

            samples = new ArrayList<Sample>();

            int sampleCount = in.nextInt();
            for (int i = 0; i < sampleCount; i++) {
                int sampleId = in.nextInt();
                int carriedBy = in.nextInt();
                int rank = in.nextInt();
                String expertiseGain = in.next();
                int health = in.nextInt();
                int costA = in.nextInt();
                int costB = in.nextInt();
                int costC = in.nextInt();
                int costD = in.nextInt();
                int costE = in.nextInt();

                Sample thisSample = new Sample(sampleId, carriedBy, expertiseGain, health, rank, costA, costB, costC, costD, costE);
                samples.add(thisSample);
                switch (carriedBy)
                {
                    case -1:

                        break;
                    case 0:

                        break;
                    case 1:
                        break;
                }
            }


            Collections.sort(samples, Collections.reverseOrder());


          /*  for (Sample s : samples) {
                System.err.println(s);
            } */

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");


            Sample s = null ;
            int id = -1;
            for (Sample e : samples) {
                if(e.carriedBy == -1)
                {
                    id = e.sampleId;
                    s = e;
                    break;
                }
            }

            Robot robot = new Robot();
//            System.err.println("target: " + String.join(" ", robot.target.toString()));
            robot.GoTo("DIAGNOSIS");
            System.err.println("sample iidddd" + s.sampleId);
            System.err.println("sample iidddd" + s.carriedBy);
            robot.Take(s);
            robot.GoTo("MOLECULES");
            robot.TakeMolecules();
            robot.GoTo("LABORATORY");
            System.out.println("CONNECT " + s.sampleId);



        }

    }

}






