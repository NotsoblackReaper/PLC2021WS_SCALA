class Database extends Warenkorb {
  private var storedItems:Array[StoreItem]=new Array[StoreItem](4)

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
    val indices:Array[Int]=new Array[Int](storedItems.length)
    var found_count:Int=0
    for(i <- storedItems.indices)
      if(storedItems(i)!=null&&storedItems(i).name==name){
        storedItems(i).logAction("gefunden",storedItems(i).name)
        indices(found_count)=i
        found_count+=1
      }
    if(found_count==0)
    throw new RuntimeException(name+" nicht gefunden")
    val found: Array[StoreItem] = new Array[StoreItem](found_count)
    for(i <- 0 until found_count-1)
      found(i)=storedItems(indices(i))
    found
  }

  override def sortByValueAsc(): Array[StoreItem] = {
    val tmp:Array[StoreItem]=storedItems.clone().filter(_!=null)
    tmp.sortBy(_.value)
  }

  override def sortByValueDesc(): Array[StoreItem] = {
    val tmp:Array[StoreItem]=storedItems.clone().filter(_!=null)
    tmp.sortWith(_.value>_.value)
  }

  override def store(item: StoreItem): Array[StoreItem] = {
    for(i <- storedItems.indices)
      if(storedItems(i)==null){
        storedItems(i)=item
        item.logAction("gespeichert",item.name)
        return storedItems
      }
    val tmp:Array[StoreItem]=new Array[StoreItem](storedItems.length+4)
    for(i <- storedItems.indices)
      tmp(i)=storedItems(i)
    tmp(storedItems.length)=item
    storedItems=tmp
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
