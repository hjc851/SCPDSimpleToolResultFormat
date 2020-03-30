package me.haydencheers.strf.beans

@NoArg
data class FileComparisonResult (
    var lhs: String,
    var rhs: String,
    var similarity: Double
)