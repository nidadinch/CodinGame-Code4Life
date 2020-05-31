import java.awt.Robot
import math._
import scala.util._
import scala.io.StdIn._


// Module class for modules

abstract class Module {
//decision method for when robot makes decision
  def decision (robot: Robot) = {

  }

}

//target = StartPoint class
class StartPoint extends Module {

  override def decision(robot: Robot): Unit = {
  
  // robot goes DIAGNOSIS when in start position
    robot.GoTo("DIAGNOSIS")
  }

}

//target = Diagnosis class
class Diagnosis extends Module {

  override def decision(robot: Robot): Unit = {
  
    // robot takes best samples
    robot.Take(s)
    // robot goes MOLECULES when in diagnosis and if robot took samples 
    robot.GoTo("MOLECULES")
  }

}

class Molecules extends Module {

  override def decision(robot: Robot): Unit = {
  
     // robot takes molecules for taken samples
     robot.TakeMolecules()
    // robot goes LABORATORY when in molecules module and if robot took molecules 
    robot.GoTo("LABORATORY")
  }

}

class Laboratory extends Module {
  
  override def decision(robot: Robot): Unit = {
  
    // robot gives molecules that taken for samples 
    robot.GiveMolecules()
    // robot goes diagnosis again when connected samples
    robot.GoTo("DIAGNOSIS")
  }

}

// Sample class
class Sample(_sampleId: Int,
             _carriedBy: Int,
             _expertiseGain: String,
             _health: Int,
             _rank: Int,
             _costA: Int,
             _costB: Int,
             _costC: Int,
             _costD: Int,
             _costE: Int) {

  var sampleId: Int = _sampleId

  var carriedBy: Int = _carriedBy

  var health: Int = _health

  var rank: Int = _rank

  var expertiseGain: String = _expertiseGain

  var costA: Int = _costA

  var costB: Int = _costB

  var costC: Int = _costC

  var costD: Int = _costD

  var costE: Int = _costE

  var totalCost: Int = _costA + _costB + _costC + _costD + _costE

  // effectiveCost calculates sample's health / cost for taking most effective sample
  var effectiveCost: Float = this.health.toFloat / this.totalCost

  def getCarriedBy(): Int = carriedBy

  def setCarriedBy(_carriedBy: Int): Unit = {
    this.carriedBy = _carriedBy
  }

  def getHealth(): Int = health

  def setHealth(_health: Int): Unit = {
    this.health = _health
  }

  def getSampleId(): Int = sampleId

  def setSampleId(_sampleId: Int): Unit = {
    this.sampleId = _sampleId
  }

  override def toString(): String = {
    "carriedBy" + carriedBy + "sampleid = " + sampleId + "- totalcost= " + totalCost + "- health= " + health +
      "-- effectiveCost = " +
      this.effectiveCost
  }
}

// class Robot
  class Robot {

    var storage: Array[Int] = _

    var expertise: Array[Int] = _

    var target: Module = _

    var score: Int = _

    var sample: Sample = _

    def this(_storage: Array[Int],
             _expertise: Array[Int],
             _target: Module,
             _score: Int) = {
      this()
      this.storage = _storage
      this.expertise = _expertise
      this.target = _target
      this.score = _score
    }

    def setTarget(_target: Module): Unit = {
      this.target = _target
    }

    def getTarget(): Module = target

    def getStorage(): Array[Int] = storage

    def setStorage(_storage: Array[Int]): Unit = {
      this.storage = _storage
    }

    def getScore(): Int = score

    def setScore(health: Int): Unit = {
      this.score += health
    }
// GoTo method for going to modules
    def GoTo(module: String): Unit = {
      println("GOTO " + module)
    }
    
// Take method for taking samples by using CONNECT
    def Take(s: Sample): Unit = {
      this.sample = s
      println("CONNECT " + s.sampleId)
    }
    
// TakeMolecules method for taking molecules that taken from Diagnosis module
    def TakeMolecules(): Unit = {
      for (i <- sample.costA to(0, -1)) {
        println("CONNECT " + "A")
      }
      for (i <- sample.costB to(0, -1)) {
        println("CONNECT " + "B")
      }
      for (i <- sample.costC to(0, -1)) {
        println("CONNECT " + "C")
      }
      for (i <- sample.costD to(0, -1)) {
        println("CONNECT " + "D")
      }
      for (i <- sample.costE to(0, -1)) {
        println("CONNECT " + "E")
      }
    }

    // GiveMolecules method for CONNECT sample to laboratory
    def GiveMolecules(): Unit = {
      println("CONNECT " + sample.sampleId)
    }

    // Update method for updating target and making desicion by robot
    def Update(): Unit = {
      target.decision(this);
    }
     

  }
// Project class for Wood1 league but i never reached
class Project(var expertise: Array[Int])



/**
   * Bring data on patient samples from the diagnosis machine to the laboratory with enough molecules to produce medicine!
   **/
  object Player extends App {
    val projectCount = readLine.toInt
    for (i <- 0 until projectCount) {
      val Array(a, b, c, d, e) = (readLine split " ").map(_.toInt)
      
      // exp array takes a,b,c,d,e
      val exp = Array(a,b,c,d,e)
      // projects list takes exp Array
      var projects: List[Project] = List(new Project((exp)))
      
    }

    // game loop
    while (true) {
      var samplesTaken: Int = 0
      
    


      for (i <- 0 until 2) {
        val Array(target, _eta, _score, _storageA, _storageB, _storageC, _storageD, _storageE, _expertiseA, _expertiseB, _expertiseC, _expertiseD, _expertiseE) = readLine split " "
        val eta = _eta.toInt
        val score = _score.toInt
        val storageA = _storageA.toInt
        val storageB = _storageB.toInt
        val storageC = _storageC.toInt
        val storageD = _storageD.toInt
        val storageE = _storageE.toInt
        val expertiseA = _expertiseA.toInt
        val expertiseB = _expertiseB.toInt
        val expertiseC = _expertiseC.toInt
        val expertiseD = _expertiseD.toInt
        val expertiseE = _expertiseE.toInt
        
        // module target for taking target
        var moduleTarget: Module = null
        
        // loop for understanding what is the target
         target match {
          case "START_POS" => moduleTarget = new StartPoint()
          case "DIAGNOSIS" => moduleTarget = new Diagnosis()
          case "MOLECULES" => moduleTarget = new Molecules()
          case "LABORATORY" => moduleTarget = new Laboratory()
        } 
        
        //str and exp arrays for taking storages and expertises
        val str = Array(storageA, storageB, storageC, storageD, storageE)
        val exp = Array(expertiseA, expertiseB, expertiseC, expertiseD, expertiseE)

        // robots list that takes exp and str arrays.
        var robots : List[Robot] = List(new Robot(str,exp, moduleTarget, score))

      }
   

      val Array(availableA, availableB, availableC, availableD, availableE) = (readLine split " ").map(_.toInt)
      val sampleCount = readLine.toInt
      for (i <- 0 until sampleCount) {
        val Array(_sampleId, _carriedBy, _rank, expertiseGain, _health, _costA, _costB, _costC, _costD, _costE) = readLine split " "
        
        // Array for avalilable molecules
        val available = Array(availableA, availableB, availableC, availableD, availableE)

        val sampleId = _sampleId.toInt
        val carriedBy = _carriedBy.toInt
        val rank = _rank.toInt
        val health = _health.toInt
        val costA = _costA.toInt
        val costB = _costB.toInt
        val costC = _costC.toInt
        val costD = _costD.toInt
        val costE = _costE.toInt

     
        // samples list for bring sample's data to samples list
        val samples: List[Sample] = List(new Sample(sampleId,
          carriedBy,
          expertiseGain,
          health,
          rank,
          costA,
          costB,
          costC,
          costD,
          costE))


   
      }


        var s: Sample = null
        var id: Int = -1
      
        for (e <- samples if e.carriedBy == -1) {
          id = e.sampleId
          s = e
        }
      
      val robot: Robot = new Robot()

      // Write an action using println
      // To debug: Console.err.println("Debug messages...")

      println("GOTO DIAGNOSIS")
      robot.GoTo("MOLECULES")
      // robot.Take(s)
      // robot.TakeMolecules()

      // Because of personal reasons, i started late for this project. First, i wrote my codes in Java for understanding the game
      // But when i was trying to understand the game, i was late. So i didn't improve my project.
      
    }


}
