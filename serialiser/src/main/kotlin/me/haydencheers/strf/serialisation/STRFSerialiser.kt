package me.haydencheers.strf.serialisation

import java.lang.IllegalArgumentException
import java.nio.file.Files
import java.nio.file.Path
import java.util.zip.ZipEntry
import java.util.zip.ZipFile
import java.util.zip.ZipInputStream
import java.util.zip.ZipOutputStream
import javax.json.bind.JsonbBuilder
import kotlin.reflect.KClass

object STRFSerialiser {
    private val jsonb = JsonbBuilder.create()
    private val ENTRY_NAME = "_strf.json"

    fun serialise(bean: Any, path: Path) {
        Files.newOutputStream(path).use { fout ->
            val zout = ZipOutputStream(fout)

            val entry = ZipEntry(ENTRY_NAME)
            zout.putNextEntry(entry)
            jsonb.toJson(bean, zout)
            jsonb.toJson(bean, System.out)
            zout.closeEntry()
        }
    }

    fun <T: Any> deserialise(path: Path, cls: KClass<T>): T {
        Files.newInputStream(path).use { fin ->
            val zin = ZipInputStream(fin)
            val entry = zin.nextEntry

            if (entry.name != "_strf.json") throw IllegalArgumentException("Invalid STRF file")

            return jsonb.fromJson(zin, cls.java)
        }
    }
}