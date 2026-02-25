package org.gorych.alice.skill.core.api

fun RequestObject.Companion.ofEmpty() = RequestObject()

fun RequestObject.Companion.of(vararg intentKeys: String) =
    RequestObject(Request("", "", NLU(listOf(), intentKeys.associateWith { "{}" })))

fun RequestObject.Companion.of(tokens: List<String>) = RequestObject(Request("", "", NLU(tokens)))
fun RequestObject.Companion.of(tokens: List<String>, sessionState: SessionState?) =
    RequestObject(Request("", "", NLU(tokens)), null, State(sessionState ?: SessionState()))

fun RequestObject.Companion.of(session: Session?): RequestObject = RequestObject(null, session)

fun RequestObject.Companion.of(sessionState: SessionState): RequestObject =
    RequestObject(null, null, State(sessionState))

//region Request Objects with intents
private val requestObjectWithIntentsRegistry: Map<String, RequestObject> = mapOf(
    Pair("g911.greeting", RequestObject.of("g911.greeting")),
    Pair("g911.parting", RequestObject.of("g911.parting")),
    Pair("g911.greeting&g911.parting", RequestObject.of("g911.greeting", "g911.parting")),

    Pair("g911.agreement", RequestObject.of("g911.agreement")),
    Pair("g911.disagreement", RequestObject.of("g911.disagreement")),
    Pair("g911.disagreement&g911.agreement", RequestObject.of("g911.disagreement", "g911.agreement")),

    Pair("g911.gratitude", RequestObject.of("g911.gratitude")),
    Pair("g911.gratitude&g911.parting", RequestObject.of("g911.gratitude", "g911.parting")),

    Pair("g911.stop", RequestObject.of("g911.stop")),
    Pair("g911.stop&g911.parting", RequestObject.of("g911.stop", "g911.parting")),

    Pair("empty", RequestObject.ofEmpty()),
)

fun RequestObject.Companion.getByIntentKey(key: String): RequestObject =
    requestObjectWithIntentsRegistry.getValue(key)
//endregion

//region Request Objects with NLU tokens
private val requestObjectWithNluTokenRegistry: Map<String, RequestObject> = mapOf(
    Pair(
        "привет",
        RequestObject.of(
            listOf("привет"),
            SessionState(currentQuestion = 7, transitionCommands = setOf("NextQuestionCommand"))
        )
    ),
    Pair("здравствуй", RequestObject.of(listOf("здравствуй"))),
    Pair("здравствуйте", RequestObject.of(listOf("здравствуйте"))),
    Pair(
        "здрАВСТвуйте",
        RequestObject.of(
            listOf("здрАВСТвуйте"),
            SessionState(currentQuestion = 7, transitionCommands = setOf("NextQuestionCommand"))
        )
    ),
    Pair("привет&здравствуй&здравствуйте", RequestObject.of(listOf("привет", "здравствуй", "здравствуйте"))),
    Pair(
        "hello",
        RequestObject.of(
            listOf("hello"),
            SessionState(currentQuestion = 7, transitionCommands = setOf("NextQuestionCommand"))
        )
    ),

    Pair("пока", RequestObject.of(listOf("пока"))),
    Pair("прощай", RequestObject.of(listOf("прощай"))),
    Pair("прОЩай", RequestObject.of(listOf("прОЩай"))),
    Pair("пока&прощай", RequestObject.of(listOf("пока", "прощай"))),
    Pair("bye", RequestObject.of(listOf("bye"))),

    Pair("стоп", RequestObject.of(listOf("стоп"))),
    Pair("хватит", RequestObject.of(listOf("хватит"))),

    Pair(
        "спасибо",
        RequestObject.of(
            listOf("спасибо"),
            SessionState(currentQuestion = 2, transitionCommands = setOf("PlayingAgreementCommand"))
        )
    ),
    Pair("благодарю", RequestObject.of(listOf("благодарю"))),
    Pair("спасибочки", RequestObject.of(listOf("спасибочки"))),
    Pair("большое_спасибо", RequestObject.of(listOf("большое_спасибо"))),
    Pair(
        "спасибо&благодарю&спасибочки",
        RequestObject.of(
            listOf("спасибо", "благодарю", "спасибочки"),
            SessionState(currentQuestion = 2, transitionCommands = setOf("PlayingAgreementCommand"))
        )
    ),

    Pair("special_characters", RequestObject.of(listOf(".", "!", ","))),
    Pair(
        "special_characters&session_state", RequestObject.of(
            listOf(".", "!", ","),
            SessionState(currentQuestion = 7, transitionCommands = setOf("NextQuestionCommand"))
        )
    ),

    Pair("empty", RequestObject.of(listOf())),
    Pair(
        "empty&session_state", RequestObject.of(
            listOf(),
            SessionState(currentQuestion = 2, transitionCommands = setOf("PlayingAgreementCommand"))
        )
    ),

    Pair("null", RequestObject.ofEmpty()),
)

fun RequestObject.Companion.getByNluTokenKey(key: String): RequestObject =
    requestObjectWithNluTokenRegistry.getValue(key)
//endregion

//region Request Objects with session
private val requestObjectWithSessionRegistry: Map<String, RequestObject> = mapOf(
    Pair("new", RequestObject.of(Session(true))),
    Pair("not_new", RequestObject.of(Session(false))),
    Pair("null", RequestObject.of(null)),
)

fun RequestObject.Companion.getBySessionKey(key: String): RequestObject = requestObjectWithSessionRegistry.getValue(key)

//endregion

//region Request Objects with session state
private val requestObjectWithSessionStateRegistry: Map<String, RequestObject> = mapOf(
    Pair(
        "disagreement_command&current_question",
        RequestObject.of(SessionState(currentQuestion = 1, transitionCommands = setOf("PlayingDisagreementCommand")))
    ),
    Pair(
        "disagreement_command&current_question_is_null",
        RequestObject.of(SessionState(currentQuestion = null, transitionCommands = setOf("PlayingDisagreementCommand")))
    ),
    Pair(
        "agreement_command&current_question_is_null",
        RequestObject.of(SessionState(currentQuestion = null, transitionCommands = setOf("PlayingAgreementCommand")))
    ),
    Pair(
        "agreement_command&current_question",
        RequestObject.of(SessionState(currentQuestion = 2, transitionCommands = setOf("PlayingAgreementCommand")))
    )
)

fun RequestObject.Companion.getBySessionStateKey(key: String): RequestObject =
    requestObjectWithSessionStateRegistry.getValue(key)
//endregion