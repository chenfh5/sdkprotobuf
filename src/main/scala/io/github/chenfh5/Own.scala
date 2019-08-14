package io.github.chenfh5

import java.io.{BufferedOutputStream, FileOutputStream}
import java.nio.file.{Files, Paths}

import pb3.person.Person
import pb3.person.Person.{PhoneNumber, PhoneType}

object Own {

  val FILENAME = "output.dat"

  def main(args: Array[String]): Unit = {
    val raw = Person(
      id = 1,
      name = "chenfh5",
      age = 18,
      phones = Array(
        PhoneNumber(number = "180", `type` = PhoneType.MOBILE),
        PhoneNumber(number = "181", `type` = PhoneType.HOME),
        PhoneNumber(number = "182", `type` = PhoneType.WORK)
      ),
      email = "chenfh5@qq.com")

    println("----begin----")
    printWithField("raw", raw)

    save(raw.toByteArray, overWritten = false)
    val decode = Person.parseFrom(read())
    printWithField("decode", decode)
    println("----end----")
  }


  def printWithField(tag: String, c1: Product): Unit = {
    print(tag + ": ")
    for (i <- 0 until c1.productElementNames.size) {
      print(c1.productElementName(i) + "->" + c1.productElement(i) + ", ")
    }
    println()
  }

  def save(byteArray: Array[Byte], overWritten: Boolean = false, fileName: String = FILENAME): Unit = {
    if (Files.exists(Paths.get(fileName)) && !overWritten) {
      println("save file already exists")
      return
    }
    val bos = new BufferedOutputStream(new FileOutputStream(fileName))
    bos.write(byteArray)
    bos.close()
  }

  def read(fileName: String = FILENAME): Array[Byte] = {
    import java.nio.file.{Files, Paths}
    Files.readAllBytes(Paths.get(fileName))
  }

}
