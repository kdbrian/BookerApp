package io.kdbrian.bookercomposeapp.data.remote.graph.vehicles

import io.kdbrian.bookercomposeapp.data.remote.graph.Clients
import io.kdbrian.bookercomposeapp.domain.repo.vehicles.GraphVehiclesRepo
import src.main.graphql.GetAllVehiclesQuery
import src.main.graphql.GetVehiclesByPriceQuery
import src.main.graphql.GetVehiclesByRouteFromAndToQuery
import src.main.graphql.GetVehiclesByRouteFromQuery
import src.main.graphql.GetVehiclesByRouteIdQuery
import src.main.graphql.GetVehiclesByRouteToQuery
import src.main.graphql.GetVehiclesBySeatRangeQuery
import src.main.graphql.GetVehiclesBySeatsQuery

class GraphVehiclesRepository : GraphVehiclesRepo {

    private val client = Clients.defaultClient

    override suspend fun getAllVehicles(): Result<GetAllVehiclesQuery.Data> {
        return try {
            val response = client.query(GetAllVehiclesQuery()).execute()
            response.data?.let {
                Result.success(it)
            } ?: run {
                Result.failure(Exception(response.exception?.message?.toString()))
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getVehiclesByRouteId(routeId: String): Result<GetVehiclesByRouteIdQuery.Data> {
        return try {
            val response = client.query(GetVehiclesByRouteIdQuery(routeId)).execute()
            response.data?.let {
                Result.success(it)
            } ?: run {
                Result.failure(Exception(response.exception?.message?.toString()))
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getVehiclesByRouteFrom(routeFrom: String): Result<GetVehiclesByRouteFromQuery.Data> {
        return try {
            val response = client.query(GetVehiclesByRouteFromQuery(routeFrom)).execute()
            response.data?.let {
                Result.success(it)
            } ?: run {
                Result.failure(Exception(response.exception?.message?.toString()))
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getVehiclesByRouteTo(routeTo: String): Result<GetVehiclesByRouteToQuery.Data> {
        return try {
            val response = client.query(GetVehiclesByRouteToQuery(routeTo)).execute()
            response.data?.let {
                Result.success(it)
            } ?: run {
                Result.failure(Exception(response.exception?.message?.toString()))
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getVehiclesByRouteFromAndTo(
        routeFrom: String,
        routeTo: String
    ): Result<GetVehiclesByRouteFromAndToQuery.Data> {
        return try {
            val response =
                client.query(GetVehiclesByRouteFromAndToQuery(routeFrom, routeTo)).execute()
            response.data?.let {
                Result.success(it)
            } ?: run {
                Result.failure(Exception(response.exception?.message?.toString()))
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getVehiclesByPrice(price: Double): Result<GetVehiclesByPriceQuery.Data> {
        return try {
            val response = client.query(GetVehiclesByPriceQuery(price)).execute()
            response.data?.let {
                Result.success(it)
            } ?: run {
                Result.failure(Exception(response.exception?.message?.toString()))
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getVehiclesBySeatCount(seats: Int): Result<GetVehiclesBySeatsQuery.Data> {
        return try {
            val response = client.query(GetVehiclesBySeatsQuery(seats)).execute()
            response.data?.let {
                Result.success(it)
            } ?: run {
                Result.failure(Exception(response.exception?.message?.toString()))
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getVehiclesBySeatRange(
        from: Int,
        to: Int
    ): Result<GetVehiclesBySeatRangeQuery.Data> {
        return try {
            val response = client.query(GetVehiclesBySeatRangeQuery(listOf(from, to))).execute()
            response.data?.let {
                Result.success(it)
            } ?: run {
                Result.failure(Exception(response.exception?.message?.toString()))
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}