package io.kdbrian.bookercomposeapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.kdbrian.bookercomposeapp.data.remote.graph.companies.GraphCompaniesRepository
import io.kdbrian.bookercomposeapp.data.util.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import src.main.graphql.GetCompaniesQuery
import src.main.graphql.GetCompanyByIdQuery
import src.main.graphql.GetCompanyByNameQuery
import src.main.graphql.SearchForCompaniesWithLocationQuery
import src.main.graphql.SearchForCompaniesWithNameQuery
import timber.log.Timber

class CompaniesViewModel : ViewModel() {

    private val companiesRepo = GraphCompaniesRepository()

    private val _companies: MutableSharedFlow<Resource<GetCompaniesQuery.Data>> =
        MutableSharedFlow()
    val companies: SharedFlow<Resource<GetCompaniesQuery.Data>>
        get() = _companies

    private val _searchedCompanies: MutableSharedFlow<Resource<GetCompanyByIdQuery.Data>> =
        MutableSharedFlow()
    val searchedCompanies: SharedFlow<Resource<GetCompanyByIdQuery.Data>>
        get() = _searchedCompanies

    private val _searchedCompanyByName: MutableSharedFlow<Resource<GetCompanyByNameQuery.Data>> =
        MutableSharedFlow()
    val searchedCompanyByName: SharedFlow<Resource<GetCompanyByNameQuery.Data>>
        get() = _searchedCompanyByName

    private val _companiesWithName: MutableSharedFlow<Resource<SearchForCompaniesWithNameQuery.Data>> =
        MutableSharedFlow()
    val companiesWithName: SharedFlow<Resource<SearchForCompaniesWithNameQuery.Data>>
        get() = _companiesWithName


    private val _companiesWithLocation: MutableSharedFlow<Resource<SearchForCompaniesWithLocationQuery.Data>> =
        MutableSharedFlow()
    val companiesWithLocation: SharedFlow<Resource<SearchForCompaniesWithLocationQuery.Data>>
        get() = _companiesWithLocation


    init {
        viewModelScope.launch {
            _companies.emit(Resource.Loading())
            delay(2_000)
            companiesRepo.getCompanies().fold(
                onSuccess = {
                    _companies.emit(
                        Resource.Success(it)
                    )
                },
                onFailure = {
                    _companies.emit(
                        Resource.Error(it.message.toString())
                    )
                }
            )
        }
    }

    fun getCompanyById(companyId: String) {
        viewModelScope.launch {
            _searchedCompanies.emit(Resource.Loading())

            companiesRepo.getCompanyById(companyId).fold(
                onSuccess = {
                    _searchedCompanies.emit(Resource.Success(it))
                },
                onFailure = {
                    _searchedCompanies.emit(Resource.Error(it.message.toString()))
                }
            )
        }
    }

    fun getCompanyByName(companyName: String) {
        viewModelScope.launch {
            _searchedCompanyByName.emit(Resource.Loading())

            companiesRepo.getCompanyByName(companyName).fold(
                onSuccess = {
                    _searchedCompanyByName.emit(Resource.Success(it))
                },
                onFailure = {
                    _searchedCompanyByName.emit(Resource.Error(it.message.toString()))
                }
            )
        }
    }

    fun searchForCompaniesWithName(companyName: String) {
        viewModelScope.launch {
            _companiesWithName.emit(Resource.Loading())

            companiesRepo.searchCompaniesWithName(companyName).fold(
                onSuccess = {
                    _companiesWithName.emit(Resource.Success(it))
                },
                onFailure = {
                    _companiesWithName.emit(Resource.Error(it.message.toString()))
                }
            )
        }
    }

    fun searchForCompaniesWithLocation(companyLocation: String) {
        viewModelScope.launch {
            _companiesWithName.emit(Resource.Loading())

            companiesRepo.searchCompaniesWithLocation(companyLocation).fold(
                onSuccess = {
                    _companiesWithLocation.emit(Resource.Success(it))
                },
                onFailure = {
                    _companiesWithLocation.emit(Resource.Error(it.message.toString()))
                }
            )
        }
    }


}