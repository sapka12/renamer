import java.io.File
import java.time.format.DateTimeFormatter

import org.apache.commons.io.FileUtils

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

  def decoratePath(filepath: String, folder: String, prefix: String): String = {
    val f = new File(filepath)
    val parent = f.getParent
    val filename = f.getName
    val path = if (parent.last == '/') parent else parent + "/"
    path + folder + "/" + prefix + "_" + filename
  }

  def copy(file: File, destination: String) = {
    FileUtils.copyFile(file, new File(destination))
    println(s"$file copied to $destination")
  }
}
