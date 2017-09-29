import javax.inject.Inject

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test.Helpers._
import play.api.test._
import controllers.TodoController
import models.{Todo, TodoRepository}
import play.api.mvc.ControllerComponents

import scala.concurrent.ExecutionContext
//import models.Todo.JsonFormats
import play.api.libs.json._
//import models.Todo.
import play.api.libs.functional.syntax._

class TodoControllerSpec  extends PlaySpec with GuiceOneAppPerTest with Injecting {

  //@Inject
 // val todoRepo: TodoRepository = new TodoRepository()

  implicit val locationWrites = new Writes[Todo] {
    def writes(todo: Todo) = Json.obj(
      "id" -> todo._id.toString,
      "title" -> todo.title,
      "completed" -> todo.completed
    )
  }

  "TodoControllerSpec GET" should {

    "list all records" in {

      val request = FakeRequest(GET, "/todos")
      val home = route(app, request).get
      status(home) mustBe OK


      contentType(home) mustBe Some("application/json")
      //contentAsString(home) must include ("Welcome to Play")
    }

//    "fetch single todo " in {
//      todoRepo.getAll.onSuccess {
//        case todos: Seq[Todo] => {
//          val request = FakeRequest(GET, controllers.routes.TodoController.getTodo(todos.head._id.get).url)
//          val home = route(app, request).get
//          status(home) mustBe OK
//        }
//      }
//    }

    "create todo" in {
      val todo = new Todo(None, "Created by test", Some(false))

      val request = FakeRequest(POST, "/todos").withJsonBody(Json.toJson(todo))
      val home = route(app, request).get
      status(home) mustBe 201

      println(contentAsString(home))
    }
  }
}