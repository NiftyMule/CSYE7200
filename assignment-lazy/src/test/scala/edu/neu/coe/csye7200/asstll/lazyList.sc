import edu.neu.coe.csye7200.asstll.MyLazyList

def headValue = {
  println("Generating head value")
  1
}

//val systemLL = (headValue #:: LazyList.empty).take(0)
//
//val customLL = MyLazyList.apply(headValue).take(0)

val ll1 = MyLazyList.apply(List(1, 2, 3))