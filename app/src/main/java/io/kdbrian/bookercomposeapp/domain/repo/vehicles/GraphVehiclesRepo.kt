package io.kdbrian.bookercomposeapp.domain.repo.vehicles

import src.main.graphql.GetAllVehiclesQuery
import src.main.graphql.GetVehiclesByPriceQuery
import src.main.graphql.GetVehiclesByRouteFromAndToQuery
import src.main.graphql.GetVehiclesByRouteFromQuery
import src.main.graphql.GetVehiclesByRouteIdQuery
import src.main.graphql.GetVehiclesByRouteToQuery
import src.main.graphql.GetVehiclesBySeatRangeQuery
import src.main.graphql.GetVehiclesBySeatsQuery

interface GraphVehiclesRepo {
    suspend fun getAllVehicles(): Result<GetAllVehiclesQuery.Data>
    suspend fun getVehiclesByRouteId(routeId: String): Result<GetVehiclesByRouteIdQuery.Data>
    suspend fun getVehiclesByRouteFrom(routeFrom: String): Result<GetVehiclesByRouteFromQuery.Data>
    suspend fun getVehiclesByRouteTo(routeTo: String): Result<GetVehiclesByRouteToQuery.Data>
    suspend fun getVehiclesByRouteFromAndTo(
        routeFrom: String,
        routeTo: String
    ): Result<GetVehiclesByRouteFromAndToQuery.Data>

    suspend fun getVehiclesByPrice(price: Double): Result<GetVehiclesByPriceQuery.Data>
    suspend fun getVehiclesBySeatCount(seats: Int): Result<GetVehiclesBySeatsQuery.Data>
    suspend fun getVehiclesBySeatRange(from: Int,to: Int): Result<GetVehiclesBySeatRangeQuery.Data>

}