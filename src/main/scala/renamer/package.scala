import java.io.File
import java.time.format.DateTimeFormatter

package object renamer {

  def date(ts: Long): String = DateTimeFormatter.BASIC_ISO_DATE.format(
    new java.sql.Date(ts).toLocalDate()
  )

  def month(ts: Long): String = DateTimeFormatter.ofPattern("YYYY-MM").format(
    new java.sql.Date(ts).toLocalDate()
  )

  def getListOfFiles(dir: String): List[File] = {
    val d = new File(dir)
    if (d.exists && d.isDirectory) {
      d.listFiles.filter(_.isFile).toList
    } else {
      List[File]()
    }
  }

  def move(filepath: String, date: String): String = {
    val f = new File(filepath)
    val folder = f.getParent
    val filename = f.getName
    val path = if (folder.last == '/') folder else folder + "/"
    path + date + "/" + date + "_" + filename
  }
}
