class StoreItem(cid:Int,cname:String,cvalue:Int) extends Artikel with Logger{
  override var id: Int = cid
  override var name: String = cname
  override var value: Int = cvalue
}
