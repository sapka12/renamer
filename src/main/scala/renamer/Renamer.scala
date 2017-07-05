package renamer

object Rename {

  def main(args: Array[String]): Unit = {
    val folder = args(0)

    getListOfFiles(folder)
      .groupBy(f => month(f.lastModified()))
      .foreach {
        case (date, files) =>
          files.foreach { file =>
            val newName = move(file.getAbsolutePath, date)
            println(s"$file copied to $newName")
//            FileUtils.copyFile(file, new File(newName))
          }
      }
  }
}
