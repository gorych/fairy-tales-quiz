package org.gorych.alice.skill.core.api

//region Request Objects with intents

fun RequestObject.Companion.emptyIntents(): RequestObject =
    RequestObject(null, null, null, listOf())

fun RequestObject.Companion.greetingIntent(): RequestObject =
    RequestObject(null, null, null, listOf("g911.greeting"))

fun RequestObject.Companion.agreementIntent(): RequestObject =
    RequestObject(null, null, null, listOf("g911.agreement"))

fun RequestObject.Companion.greetingAndPartingIntents(): RequestObject =
    RequestObject(null, null, null, listOf("g911.greeting", "g911.parting"))

private val requestObjectWithIntentsRegistry = mapOf(
    Pair("g911.greeting", RequestObject.greetingIntent()),
    Pair("g911.agreement", RequestObject.agreementIntent()),
    Pair("g911.greeting&g911.parting", RequestObject.greetingAndPartingIntents()),
    Pair("empty", RequestObject.emptyIntents()),
)
//endregion

//region Request Objects with NLU tokens

fun RequestObject.Companion.tokens_привет(): RequestObject =
    RequestObject(null, null, null, listOf("g911.greeting"), NLU(listOf("привет")))

fun RequestObject.Companion.tokens_здравствуй(): RequestObject =
    RequestObject(null, null, null, listOf("g911.greeting"), NLU(listOf("здравствуй")))

fun RequestObject.Companion.tokens_здравствуйте(): RequestObject =
    RequestObject(null, null, null, listOf("g911.greeting"), NLU(listOf("здравствуйте")))

fun RequestObject.Companion.tokens_здрАВСТвуйте(): RequestObject =
    RequestObject(null, null, null, listOf("g911.greeting"), NLU(listOf("здрАВСТвуйте")))

fun RequestObject.Companion.tokens_привет_здравствуй_здравствуйте(): RequestObject =
    RequestObject(
        null,
        null,
        null,
        listOf("g911.greeting"),
        NLU(listOf("привет", "здравствуй", "здравствуйте"))
    )

fun RequestObject.Companion.tokens_hello(): RequestObject =
    RequestObject(null, null, null, listOf("g911.greeting"), NLU(listOf("hello")))

fun RequestObject.Companion.tokens_specialCharacters(): RequestObject =
    RequestObject(null, null, null, listOf("g911.greeting"), NLU(listOf(".", "!", ",")))

fun RequestObject.Companion.tokens_empty(): RequestObject =
    RequestObject(null, null, null, listOf("g911.greeting"), NLU(listOf()))

fun RequestObject.Companion.tokens_null(): RequestObject =
    RequestObject(null, null, null, listOf("g911.greeting"), null)

fun RequestObject.Companion.getByIntentKey(key: String): RequestObject = requestObjectWithIntentsRegistry.getValue(key)

private val requestObjectWithNluTokenRegistry = mapOf(
    Pair("привет", RequestObject.tokens_привет()),
    Pair("здравствуй", RequestObject.tokens_здравствуй()),
    Pair("здравствуйте", RequestObject.tokens_здравствуйте()),
    Pair("привет&здравствуй&здравствуйте", RequestObject.tokens_привет_здравствуй_здравствуйте()),
    Pair("hello", RequestObject.tokens_hello()),
    Pair("special_characters", RequestObject.tokens_specialCharacters()),
    Pair("empty", RequestObject.tokens_empty()),
    Pair("null", RequestObject.tokens_null()),
)

fun RequestObject.Companion.getByNluTokenKey(key: String): RequestObject =
    requestObjectWithNluTokenRegistry.getValue(key)
//endregion
