package io.kdbrian.bookercomposeapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.kdbrian.bookercomposeapp.data.remote.graph.routes.GraphRoutesRepository
import io.kdbrian.bookercomposeapp.data.util.Resource
import io.kdbrian.bookercomposeapp.domain.repo.routes.GraphRoutesRepo
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import src.main.graphql.GetAllRoutesQuery
import src.main.graphql.GetScheduleRoutesQuery

class RoutesViewModel : ViewModel() {

    private val routesRepo: GraphRoutesRepo = GraphRoutesRepository()

    private val _allRoutes: MutableSharedFlow<Resource<GetAllRoutesQuery.Data>> =
        MutableSharedFlow()
    val allRoutes: SharedFlow<Resource<GetAllRoutesQuery.Data>>
        get() = _allRoutes

    private val _scheduleRoutes: MutableSharedFlow<Resource<GetScheduleRoutesQuery.Data>> =
        MutableSharedFlow()
    val scheduleRoutes: SharedFlow<Resource<GetScheduleRoutesQuery.Data>>
        get() = _scheduleRoutes

    init {
        viewModelScope.launch {
            _allRoutes.emit(Resource.Loading())

            routesRepo.getAllRoutes().fold(
                onSuccess = {
                    _allRoutes.emit(Resource.Success(it))
                },
                onFailure = {
                    _allRoutes.emit(Resource.Error(it.message.toString()))
                }
            )
        }
    }


    fun getScheduleRoutes(scheduleId: String) {
        viewModelScope.launch {
            _scheduleRoutes.emit(Resource.Loading())
            routesRepo.getRoutesByScheduleId(scheduleId).fold(
                onSuccess = {
                    _scheduleRoutes.emit(Resource.Success(it))
                },
                onFailure = {
                    _scheduleRoutes.emit(Resource.Error(it.message.toString()))
                }
            )
        }
    }


}