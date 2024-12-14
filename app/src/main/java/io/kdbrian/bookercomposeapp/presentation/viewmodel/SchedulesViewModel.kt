package io.kdbrian.bookercomposeapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.kdbrian.bookercomposeapp.data.remote.graph.schedules.GraphSchedulesRepository
import io.kdbrian.bookercomposeapp.data.util.Resource
import io.kdbrian.bookercomposeapp.domain.repo.schedules.GraphSchedulesRepo
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import src.main.graphql.GetCompanySchedulesQuery
import src.main.graphql.GetSchedulesQuery

class SchedulesViewModel : ViewModel() {

    private val schedulesRepo: GraphSchedulesRepo = GraphSchedulesRepository()

    private val _allSchedules: MutableSharedFlow<Resource<GetSchedulesQuery.Data>> =
        MutableSharedFlow()
    val allSchedules: SharedFlow<Resource<GetSchedulesQuery.Data>>
        get() = _allSchedules

    private val _companySchedules: MutableSharedFlow<Resource<GetCompanySchedulesQuery.Data>> =
        MutableSharedFlow()
    val companySchedules: SharedFlow<Resource<GetCompanySchedulesQuery.Data>>
        get() = _companySchedules

    init {
        viewModelScope.launch {
            _allSchedules.emit(Resource.Loading())
            schedulesRepo.getSchedules().fold(
                onSuccess = {
                    _allSchedules.emit(Resource.Success(it))
                },
                onFailure = {
                    _allSchedules.emit(Resource.Error(it.message.toString()))
                }
            )
        }
    }

    fun getCompanySchedules(companyId: String) {
        viewModelScope.launch {
            _companySchedules.emit(Resource.Loading())

            schedulesRepo.getSchedulesByCompanyId(companyId).fold(
                onSuccess = {
                    _companySchedules.emit(Resource.Success(it))
                },
                onFailure = {
                    _companySchedules.emit(Resource.Error(it.message.toString()))
                }
            )
        }
    }
}