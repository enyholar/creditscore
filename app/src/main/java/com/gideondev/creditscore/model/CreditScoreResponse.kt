package com.gideondev.creditscore.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CreditScoreResponse(

	@field:SerializedName("dashboardStatus")
	val dashboardStatus: String? = null,

	@field:SerializedName("personaType")
	val personaType: String? = null,

	@field:SerializedName("coachingSummary")
	val coachingSummary: CoachingSummary? = null,

	@field:SerializedName("augmentedCreditScore")
	val augmentedCreditScore: Any? = null,

	@field:SerializedName("creditReportInfo")
	val creditReportInfo: CreditReportInfo? = null,

	@field:SerializedName("accountIDVStatus")
	val accountIDVStatus: String? = null
):Serializable

data class CreditReportInfo(

	@field:SerializedName("numPositiveScoreFactors")
	val numPositiveScoreFactors: Int? = null,

	@field:SerializedName("changeInShortTermDebt")
	val changeInShortTermDebt: Int? = null,

	@field:SerializedName("clientRef")
	val clientRef: String? = null,

	@field:SerializedName("currentShortTermDebt")
	val currentShortTermDebt: Int? = null,

	@field:SerializedName("percentageCreditUsedDirectionFlag")
	val percentageCreditUsedDirectionFlag: Int? = null,

	@field:SerializedName("score")
	val score: Int? = null,

	@field:SerializedName("currentShortTermNonPromotionalDebt")
	val currentShortTermNonPromotionalDebt: Int? = null,

	@field:SerializedName("currentLongTermDebt")
	val currentLongTermDebt: Int? = null,

	@field:SerializedName("changedScore")
	val changedScore: Int? = null,

	@field:SerializedName("currentShortTermCreditLimit")
	val currentShortTermCreditLimit: Int? = null,

	@field:SerializedName("percentageCreditUsed")
	val percentageCreditUsed: Int? = null,

	@field:SerializedName("daysUntilNextReport")
	val daysUntilNextReport: Int? = null,

	@field:SerializedName("currentLongTermCreditLimit")
	val currentLongTermCreditLimit: Any? = null,

	@field:SerializedName("currentLongTermCreditUtilisation")
	val currentLongTermCreditUtilisation: Any? = null,

	@field:SerializedName("equifaxScoreBand")
	val equifaxScoreBand: Int? = null,

	@field:SerializedName("minScoreValue")
	val minScoreValue: Int? = null,

	@field:SerializedName("currentShortTermCreditUtilisation")
	val currentShortTermCreditUtilisation: Int? = null,

	@field:SerializedName("changeInLongTermDebt")
	val changeInLongTermDebt: Int? = null,

	@field:SerializedName("equifaxScoreBandDescription")
	val equifaxScoreBandDescription: String? = null,

	@field:SerializedName("monthsSinceLastDelinquent")
	val monthsSinceLastDelinquent: Int? = null,

	@field:SerializedName("numNegativeScoreFactors")
	val numNegativeScoreFactors: Int? = null,

	@field:SerializedName("hasEverBeenDelinquent")
	val hasEverBeenDelinquent: Boolean? = null,

	@field:SerializedName("currentLongTermNonPromotionalDebt")
	val currentLongTermNonPromotionalDebt: Int? = null,

	@field:SerializedName("scoreBand")
	val scoreBand: Int? = null,

	@field:SerializedName("hasEverDefaulted")
	val hasEverDefaulted: Boolean? = null,

	@field:SerializedName("maxScoreValue")
	val maxScoreValue: Int? = null,

	@field:SerializedName("status")
	val status: String? = null,

	@field:SerializedName("monthsSinceLastDefaulted")
	val monthsSinceLastDefaulted: Int? = null
):Serializable

data class CoachingSummary(

	@field:SerializedName("activeChat")
	val activeChat: Boolean? = null,

	@field:SerializedName("numberOfTodoItems")
	val numberOfTodoItems: Int? = null,

	@field:SerializedName("activeTodo")
	val activeTodo: Boolean? = null,

	@field:SerializedName("numberOfCompletedTodoItems")
	val numberOfCompletedTodoItems: Int? = null,

	@field:SerializedName("selected")
	val selected: Boolean? = null
):Serializable
