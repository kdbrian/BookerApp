package io.kdbrian.bookercomposeapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.kdbrian.bookercomposeapp.data.remote.graph.vehicles.GraphVehiclesRepository
import io.kdbrian.bookercomposeapp.data.util.Resource
import io.kdbrian.bookercomposeapp.domain.repo.vehicles.GraphVehiclesRepo
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import src.main.graphql.GetAllVehiclesQuery
import src.main.graphql.GetVehiclesByPriceQuery
import src.main.graphql.GetVehiclesByRouteFromAndToQuery
import src.main.graphql.GetVehiclesByRouteFromQuery
import src.main.graphql.GetVehiclesByRouteIdQuery
import src.main.graphql.GetVehiclesByRouteToQuery
import src.main.graphql.GetVehiclesBySeatRangeQuery
import src.main.graphql.GetVehiclesBySeatsQuery

class VehiclesViewModel : ViewModel() {

    private val vehiclesRepo: GraphVehiclesRepo = GraphVehiclesRepository()

    private val _allVehicles: MutableSharedFlow<Resource<GetAllVehiclesQuery.Data>> =
        MutableSharedFlow()
    val allVehicles: MutableSharedFlow<Resource<GetAllVehiclesQuery.Data>> = _allVehicles

    private val _vehiclesBySeats: MutableSharedFlow<Resource<GetVehiclesBySeatsQuery.Data>> =
        MutableSharedFlow()
    val vehiclesBySeats: MutableSharedFlow<Resource<GetVehiclesBySeatsQuery.Data>> =
        _vehiclesBySeats

    private val _vehiclesByPrice: MutableSharedFlow<Resource<GetVehiclesByPriceQuery.Data>> =
        MutableSharedFlow()
    val vehiclesByPrice: MutableSharedFlow<Resource<GetVehiclesByPriceQuery.Data>> =
        _vehiclesByPrice

    private val _vehicleByRouteId: MutableSharedFlow<Resource<GetVehiclesByRouteIdQuery.Data>> =
        MutableSharedFlow()
    val vehicleByRouteId: MutableSharedFlow<Resource<GetVehiclesByRouteIdQuery.Data>> =
        _vehicleByRouteId

    private val _vehiclesByRouteFrom: MutableSharedFlow<Resource<GetVehiclesByRouteFromQuery.Data>> =
        MutableSharedFlow()
    val vehiclesByRouteFrom: MutableSharedFlow<Resource<GetVehiclesByRouteFromQuery.Data>> =
        _vehiclesByRouteFrom

    private val _vehiclesByRouteTo: MutableSharedFlow<Resource<GetVehiclesByRouteToQuery.Data>> =
        MutableSharedFlow()
    val vehiclesByRouteTo: MutableSharedFlow<Resource<GetVehiclesByRouteToQuery.Data>> =
        _vehiclesByRouteTo

    private val _vehiclesByRouteFromAndTo: MutableSharedFlow<Resource<GetVehiclesByRouteFromAndToQuery.Data>> =
        MutableSharedFlow()
    val vehiclesByRouteFromAndTo: MutableSharedFlow<Resource<GetVehiclesByRouteFromAndToQuery.Data>> =
        _vehiclesByRouteFromAndTo

    private val _vehiclesBySeatRange: MutableSharedFlow<Resource<GetVehiclesBySeatRangeQuery.Data>> =
        MutableSharedFlow()
    val vehiclesBySeatRange: MutableSharedFlow<Resource<GetVehiclesBySeatRangeQuery.Data>> =
        _vehiclesBySeatRange

    init {
        getAllVehicles()
    }

    private fun getAllVehicles() {
        viewModelScope.launch {
            _allVehicles.emit(Resource.Loading())

            vehiclesRepo.getAllVehicles().fold(
                onSuccess = {
                    _allVehicles.emit(Resource.Success(it))
                },
                onFailure = {
                    _allVehicles.emit(Resource.Error(it.message.toString()))
                }
            )
        }
    }

    fun getRouteVehicles(routeId: String) {
        viewModelScope.launch {
            _vehicleByRouteId.emit(Resource.Loading())
            vehiclesRepo.getVehiclesByRouteId(routeId).fold(
                onSuccess = {
                    _vehicleByRouteId.emit(Resource.Success(it))
                },
                onFailure = {
                    _vehicleByRouteId.emit(Resource.Error(it.message.toString()))
                }
            )
        }
    }

    fun getVehiclesByPrice(price: Double) {
        viewModelScope.launch {
            _vehiclesByPrice.emit(Resource.Loading())

            vehiclesRepo.getVehiclesByPrice(price).fold(
                onSuccess = {
                    _vehiclesByPrice.emit(Resource.Success(it))
                },

                onFailure = {
                    _vehiclesByPrice.emit(Resource.Error(it.message.toString()))
                }
            )
        }
    }

    fun getVehiclesBySeats(seatCount: Int) {
        viewModelScope.launch {
            _vehiclesBySeats.emit(Resource.Loading())

            vehiclesRepo.getVehiclesBySeatCount(seatCount).fold(
                onSuccess = {
                    _vehiclesBySeats.emit(Resource.Success(it))
                },

                onFailure = {
                    _vehiclesBySeats.emit(Resource.Error(it.message.toString()))
                }
            )
        }
    }

    fun getVehiclesByRouteFrom(routeFrom: String) {
        viewModelScope.launch {
            _vehiclesByRouteFrom.emit(Resource.Loading())
            vehiclesRepo.getVehiclesByRouteFrom(routeFrom).fold(
                onSuccess = {
                    _vehiclesByRouteFrom.emit(Resource.Success(it))
                },
                onFailure = {
                    _vehiclesByRouteFrom.emit(Resource.Error(it.message.toString()))
                }
            )

        }
    }

    fun getVehiclesByRouteTo(routeTo: String) {
        viewModelScope.launch {
            _vehiclesByRouteTo.emit(Resource.Loading())
            vehiclesRepo.getVehiclesByRouteTo(routeTo).fold(
                onSuccess = {
                    _vehiclesByRouteTo.emit(Resource.Success(it))
                },
                onFailure = {
                    _vehiclesByRouteTo.emit(Resource.Error(it.message.toString()))
                }
            )

        }
    }

    fun getVehiclesByRouteFromAndTo(routeFrom: String, routeTo: String) {
        viewModelScope.launch {
            _vehiclesByRouteFromAndTo.emit(Resource.Loading())
            vehiclesRepo.getVehiclesByRouteFromAndTo(routeFrom, routeTo).fold(
                onSuccess = {
                    _vehiclesByRouteFromAndTo.emit(Resource.Success(it))
                },
                onFailure = {
                    _vehiclesByRouteFromAndTo.emit(Resource.Error(it.message.toString()))
                }
            )

        }
    }

    fun getVehiclesSeatRange(from: Int, to: Int) {
        viewModelScope.launch {
            _vehiclesBySeatRange.emit(Resource.Loading())
            vehiclesRepo.getVehiclesBySeatRange(from, to).fold(
                onSuccess = {
                    _vehiclesBySeatRange.emit(Resource.Success(it))
                },
                onFailure = {
                    _vehiclesBySeatRange.emit(Resource.Error(it.message.toString()))
                }
            )

        }
    }


}