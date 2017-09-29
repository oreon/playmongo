//package base
//
//import javax.inject.Inject
//
//import play.api.libs.json.Json
//import play.modules.reactivemongo.ReactiveMongoApi
//import reactivemongo.api.ReadPreference
//import reactivemongo.api.commands.{UpdateWriteResult, WriteResult}
//import reactivemongo.bson.{BSONDocument, BSONObjectID}
//import reactivemongo.play.json._
//import reactivemongo.play.json.collection.JSONCollection
//import models.{BaseObject}
//
//import scala.concurrent.{ExecutionContext, Future}
//
//object JsonFormatsObj{
//  import play.api.libs.json._
//
//  implicit val todoFormat: OFormat[BaseObject] = Json.format[BaseObject]
//}
//
//abstract class BaseRepo[BaseObject] @Inject()(implicit ec: ExecutionContext, reactiveMongoApi: ReactiveMongoApi){
//
//  import JsonFormatsObj._
//
//  def getCollection: Future[JSONCollection]
//
//  implicit def getFormat
//
//
//   def getAll: Future[Seq[BaseObject]] = {
//    val query = Json.obj()
//    implicit val format = getFormat
//    getCollection.flatMap(_.find(query)
//      .cursor[BaseObject](ReadPreference.primary)
//      .collect[Seq]()
//    )
//  }
//
//  def getById(id: BSONObjectID): Future[Option[BaseObject]] = {
//    val query = BSONDocument("_id" -> id)
//    getCollection.flatMap(_.find(query).one[BaseObject])
//  }
//
//  def add(todo: BaseObject): Future[WriteResult] = {
//    getCollection.flatMap(_.insert(todo))
//  }
//
//  /*
//  def update(id: BSONObjectID, todo: A): Future[Option[A]] = {
//
//    val selector = BSONDocument("_id" -> id)
//    val updateModifier = BSONDocument(
//      "$set" -> BSONDocument(
//        "title" -> todo.title,
//        "completed" -> todo.completed)
//    )
//
//    getCollection.flatMap(
//      _.findAndUpdate(selector, updateModifier, fetchNewObject = true).map(_.result[A])
//    )
//  }*/
//
//  /*
//  def deleteA(id: BSONObjectID): Future[Option[A]] = {
//    val selector = BSONDocument("_id" -> id)
//    getCollection.flatMap(_.findAndRemove(selector).map(_.result[A]))
//  }*/
//
//}
