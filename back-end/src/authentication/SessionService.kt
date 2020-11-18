@file:OptIn(KtorExperimentalAPI::class)

package com.gabriel.kspanel.project.rest.authentication

import io.ktor.application.*
import io.ktor.routing.*
import io.ktor.util.*
import io.ktor.util.pipeline.*

private class AuthenticatedRouteSelector: RouteSelector(RouteSelectorEvaluation.qualityConstant) {

    override fun evaluate(context: RoutingResolveContext, segmentIndex: Int) =
        RouteSelectorEvaluation.Constant
}

@OptIn(KtorExperimentalAPI::class)
fun Route.authenticated(route: Route.() -> Unit): Route = createChild(AuthenticatedRouteSelector()).apply {
    intercept(ApplicationCallPipeline.Features) {
        validate {
            finish()
        }
    }
    return apply(route)
}

@OptIn(KtorExperimentalAPI::class)
private fun PipelineContext<Unit, ApplicationCall>.validate(callback: (String) -> Unit) {
    // TODO
}