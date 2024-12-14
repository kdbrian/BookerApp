package io.kdbrian.bookercomposeapp.domain.repo.schedules

import src.main.graphql.GetCompanySchedulesQuery
import src.main.graphql.GetSchedulesQuery

interface GraphSchedulesRepo {
    suspend fun getSchedules(): Result<GetSchedulesQuery.Data>
    suspend fun getSchedulesByCompanyId(companyId: String): Result<GetCompanySchedulesQuery.Data>
}