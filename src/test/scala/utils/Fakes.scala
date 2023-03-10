package utils

import java.time.Instant
import java.util.UUID
import scala.collection.Seq
import scala.util.Random

trait Fakes {
  final private val Tlds = Seq("com", "org", "net", "edu", "gov", "io", "cool")

  final protected val rnd = new Random()

  def uuid(): UUID = UUID.randomUUID()

  def string(length: Int = 10): String = Random.alphanumeric.take(length).mkString

  def url(): String = url(bool())

  def url(secure: Boolean) = s"${if (secure) "https" else "http"}://${string()}.${tld()}"

  def email(): String = s"${string(5)}@${string(5)}.${tld()}"

  def instant(): Instant = Instant.now()

  def bool(): Boolean = rnd.nextBoolean()

  def double(): Double = rnd.nextDouble()

  def float(): Double = rnd.nextFloat().toDouble

  def int(n: Int): Int = rnd.nextInt(n)

  def int(): Int = rnd.nextInt()

  def long(): Long = rnd.nextLong()

  def short(): Short = int(Short.MaxValue + 1).asInstanceOf[Short]

  def bigDecimal(): BigDecimal = BigDecimal(double())

  def tld() = Tlds(int(Tlds.length))
}
