package io.kdbrian.bookercomposeapp.data.remote.graph.routes

import io.kdbrian.bookercomposeapp.data.remote.graph.Clients
import io.kdbrian.bookercomposeapp.domain.repo.routes.GraphRoutesRepo
import src.main.graphql.GetAllRoutesQuery
import src.main.graphql.GetScheduleRoutesQuery

class GraphRoutesRepository : GraphRoutesRepo {

    private val client = Clients.defaultClient

    override suspend fun getAllRoutes(): Result<GetAllRoutesQuery.Data> {
        return try {
            val routes = client.query(GetAllRoutesQuery()).execute()
            routes.data?.let {
                Result.success(it)
            } ?: run {
                Result.failure(
                    Exception(
                        routes.exception?.message?.toString()
                    )
                )
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getRoutesByScheduleId(scheduleId: String): Result<GetScheduleRoutesQuery.Data> {
        return try {
            val routes = client.query(GetScheduleRoutesQuery(scheduleId)).execute()

            routes.data?.let {
                Result.success(it)
            } ?: run {
                Result.failure(
                    Exception(
                        routes.exception?.message?.toString()
                    )
                )
            }
        } catch (e: Exception) {
            Result.failure(e)
        }

    }
}