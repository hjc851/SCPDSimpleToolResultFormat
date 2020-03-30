package me.haydencheers.strf.beans

@NoArg
data class PairwiseComparisonResult (
    var lhs: String,
    var rhs: String,
    var fileComparisons: List<FileComparisonResult>,
    var similarity: Double
)