import org.scalatest.{FlatSpec, Matchers}
import renamer._

class RenamerSpec extends FlatSpec with Matchers {

  behavior of "timestamp to month"

  it should "return 1970-01 for 0" in {
    month(0) shouldBe "1970-01"
  }

  behavior of "timestamp to date"

  it should "return 19700101 for 0" in {
    date(0) shouldBe "19700101"
  }

  it should "return 20170617" in {
    val _20170617 = 1497733877711L
    date(_20170617) shouldBe "20170617"
  }

  behavior of "move"

  it should "rename and put to folder" in {
    val originalFilePath = "/file.txt"
    val month = "1970-01"
    val date = "19700101"
    decoratePath(originalFilePath, month, date) shouldBe "/1970-01/19700101_file.txt"
  }

  it should "rename and put to folder - example 2" in {
    val originalFilePath = "/deep/folder/structure/file.txt"
    val month = "1970-01"
    val date = "19700101"
    decoratePath(originalFilePath, month, date) shouldBe "/deep/folder/structure/1970-01/19700101_file.txt"
  }

}
