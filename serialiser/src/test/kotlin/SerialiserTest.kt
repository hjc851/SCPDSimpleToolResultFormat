import me.haydencheers.strf.beans.BatchEvaluationResult
import me.haydencheers.strf.beans.FileComparisonResult
import me.haydencheers.strf.beans.PairwiseComparisonResult
import me.haydencheers.strf.serialisation.STRFSerialiser
import org.junit.Assert
import org.junit.Test
import java.nio.file.Files

class SerialiserTest {
    @Test
    fun testSerialisation() {
        val bean = BatchEvaluationResult(
            "test",
            setOf("id1", "id2"),
            listOf(
                PairwiseComparisonResult(
                    "id1",
                    "id2",
                    listOf(
                        FileComparisonResult(
                            "f1.java",
                            "f2.java",
                            1.0
                        )
                    ),
                    1.0
                )
            )
        )

        val tmp = Files.createTempFile("strftest", ".strf")
        try {
            STRFSerialiser.serialise(bean, tmp)
        } finally {
            Files.delete(tmp)
        }
    }

    @Test
    fun testDeserialisation() {
        val bean = BatchEvaluationResult(
            "test",
            setOf("id1", "id2"),
            listOf(
                PairwiseComparisonResult(
                    "id1",
                    "id2",
                    listOf(
                        FileComparisonResult(
                            "f1.java",
                            "f2.java",
                            1.0
                        )
                    ),
                    1.0
                )
            )
        )

        val tmp = Files.createTempFile("strftest", ".strf")
        try {
            STRFSerialiser.serialise(bean, tmp)
            val dsbean = STRFSerialiser.deserialise(tmp, BatchEvaluationResult::class)
            Assert.assertEquals(bean, dsbean)

        } finally {
            Files.delete(tmp)
        }
    }
}