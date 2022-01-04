trait Artikel {
  var id: Int
  var name: String
  var value: Int
}

trait Logger {
  def logAction(actionName: String, name: String): Unit = { }
}


class StoreItem(cid:Int,cname:String,cvalue:Int) extends Artikel with Logger{
  override var id: Int = cid
  override var name: String = cname
  override var value: Int = cvalue
}
