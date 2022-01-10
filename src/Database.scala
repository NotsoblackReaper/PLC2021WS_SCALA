class Database extends Warenkorb {
  var storedItems:Array[StoreItem]=new Array[StoreItem](4)

  override def delete(id: Int): Array[StoreItem] = {
    for(i <- storedItems.indices)
      if(storedItems(i)!=null&&storedItems(i).id==id){
        storedItems(i).logAction("gelöscht",storedItems(i).name)
        storedItems(i)=null
        return storedItems
      }
    throw new RuntimeException("Id "+id+" nicht gefunden")
  }

  override def search(name: String): Array[StoreItem] = {
    val tmp=storedItems.filter(_.name==name)
    if(tmp.length>0){
      for(i <- tmp)
        i.logAction(i.name,"gefunden")
      tmp
    }else{
      throw new RuntimeException(name+" nicht gefunden")
    }
  }

  override def sortByValueAsc(): Array[StoreItem] = {
    storedItems.filter(_!=null).sortBy(_.value)
  }

  override def sortByValueDesc(): Array[StoreItem] = {
    storedItems.filter(_!=null).sortWith(_.value>_.value)
  }

  override def store(item: StoreItem): Array[StoreItem] = {
    val index=storedItems.indexWhere(_==null)
    if(index>=0)
      storedItems(index)=item
    else
      storedItems:+item
    item.logAction("gespeichert",item.name)
    storedItems
  }

  override def sumUp(): Int = {
    var sum=0
    for(i: StoreItem <- storedItems) {
      if(i!=null)
      sum+=i.value
    }
    sum
  }
}
