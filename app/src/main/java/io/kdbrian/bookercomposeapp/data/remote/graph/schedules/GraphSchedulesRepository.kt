package io.kdbrian.bookercomposeapp.data.remote.graph.schedules

import io.kdbrian.bookercomposeapp.data.remote.graph.Clients
import io.kdbrian.bookercomposeapp.domain.repo.schedules.GraphSchedulesRepo
import src.main.graphql.GetCompanySchedulesQuery
import src.main.graphql.GetSchedulesQuery

class GraphSchedulesRepository : GraphSchedulesRepo {

    private val client = Clients.defaultClient

    override suspend fun getSchedules(): Result<GetSchedulesQuery.Data> {
        return try {
            val schedules = client.query(GetSchedulesQuery()).execute()

            schedules.data?.let {
                Result.success(it)
            } ?: run {
                Result.failure(
                    Exception(
                        schedules.exception?.message?.toString()
                    )
                )
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getSchedulesByCompanyId(companyId: String): Result<GetCompanySchedulesQuery.Data> {
        return try {
            val schedules = client.query(GetCompanySchedulesQuery(companyId)).execute()
            schedules.data?.let {
                Result.success(it)
            } ?: run {
                Result.failure(
                    Exception(
                        schedules.exception?.message?.toString()
                    )
                )
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}