package io.kdbrian.bookercomposeapp.domain.repo.routes

import src.main.graphql.GetAllRoutesQuery
import src.main.graphql.GetScheduleRoutesQuery

interface GraphRoutesRepo {
    suspend fun getAllRoutes(): Result<GetAllRoutesQuery.Data>
    suspend fun getRoutesByScheduleId(scheduleId: String): Result<GetScheduleRoutesQuery.Data>
}