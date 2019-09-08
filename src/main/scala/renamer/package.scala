import java.io.File
import java.time.format.DateTimeFormatter

import cats.implicits._
import cats.effect.IO
import org.apache.commons.io.FileUtils

package object renamer {

  def date(ts: Long): String = DateTimeFormatter.BASIC_ISO_DATE.format(
    new java.sql.Date(ts).toLocalDate()
  )

  def month(ts: Long): String = DateTimeFormatter.ofPattern("YYYY-MM").format(
    new java.sql.Date(ts).toLocalDate()
  )

  def getListOfFiles(dir: String): IO[List[File]] = for {
    directory <- IO(new File(dir))
    isDir <- IO(directory.exists && directory.isDirectory)
    files <- IO(if (isDir) directory.listFiles.filter(_.isFile).toList else List.empty[File])
  } yield files

  def decoratePath(filepath: String, folder: String, prefix: String): String = {
    val f = new File(filepath)
    val parent = f.getParent
    val filename = f.getName
    val path = if (parent.last.toString == File.separator) parent else parent + File.separator
    path + folder + File.separator + prefix + "_" + filename
  }

  def copy(file: File, destination: String): IO[Unit] = IO{
    FileUtils.copyFile(file, new File(destination))
    println(s"$file copied to $destination")
  }

  def copyFiles(files: List[File]): IO[List[Unit]] = files.map{file =>
    copy(
      file,
      decoratePath(
        file.getAbsolutePath,
        month(file.lastModified),
        date(file.lastModified)
      )
    )
  }.sequence

}
