package me.haydencheers.strf.beans

@NoArg
data class BatchEvaluationResult (
    var name: String,
    var ids: Set<String>,
    var comparisons: List<PairwiseComparisonResult>
)