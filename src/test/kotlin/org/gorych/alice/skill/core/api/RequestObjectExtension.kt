package org.gorych.alice.skill.core.api

fun RequestObject.Companion.ofEmpty() = RequestObject()

fun RequestObject.Companion.of(vararg intentKeys: String) =
    RequestObject(Request("", NLU(listOf(), intentKeys.associateWith { "{}" })))

fun RequestObject.Companion.of(tokens: List<String>) = RequestObject(Request("", NLU(tokens)))

fun RequestObject.Companion.of(session: Session?): RequestObject = RequestObject(null, session)

//region Request Objects with intents
private val requestObjectWithIntentsRegistry: Map<String, RequestObject> = mapOf(
    Pair("g911.greeting", RequestObject.of("g911.greeting")),
    Pair("g911.parting", RequestObject.of("g911.parting")),
    Pair("g911.agreement", RequestObject.of("g911.agreement")),
    Pair("g911.greeting&g911.parting", RequestObject.of("g911.greeting", "g911.parting")),
    Pair("empty", RequestObject.ofEmpty()),
)

fun RequestObject.Companion.getByIntentKey(key: String): RequestObject =
    requestObjectWithIntentsRegistry.getValue(key)
//endregion

//region Request Objects with NLU tokens
private val requestObjectWithNluTokenRegistry: Map<String, RequestObject> = mapOf(
    Pair("привет", RequestObject.of(listOf("привет"))),
    Pair("здравствуй", RequestObject.of(listOf("здравствуй"))),
    Pair("здравствуйте", RequestObject.of(listOf("здравствуйте"))),
    Pair("здрАВСТвуйте", RequestObject.of(listOf("здрАВСТвуйте"))),
    Pair("привет&здравствуй&здравствуйте", RequestObject.of(listOf("привет", "здравствуй", "здравствуйте"))),
    Pair("hello", RequestObject.of(listOf("hello"))),

    Pair("пока", RequestObject.of(listOf("пока"))),
    Pair("прощай", RequestObject.of(listOf("прощай"))),
    Pair("прОЩай", RequestObject.of(listOf("прОЩай"))),
    Pair("пока&прощай", RequestObject.of(listOf("пока", "прощай"))),
    Pair("bye", RequestObject.of(listOf("bye"))),

    Pair("special_characters", RequestObject.of(listOf(".", "!", ","))),
    Pair("empty", RequestObject.of(listOf())),
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