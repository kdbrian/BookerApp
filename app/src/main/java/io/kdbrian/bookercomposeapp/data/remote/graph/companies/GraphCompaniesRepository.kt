package io.kdbrian.bookercomposeapp.data.remote.graph.companies

import io.kdbrian.bookercomposeapp.data.remote.graph.Clients
import io.kdbrian.bookercomposeapp.domain.repo.companies.GraphCompaniesRepo
import src.main.graphql.GetCompaniesQuery
import src.main.graphql.GetCompanyByIdQuery
import src.main.graphql.GetCompanyByNameQuery
import src.main.graphql.SearchForCompaniesWithLocationQuery
import src.main.graphql.SearchForCompaniesWithNameQuery
import timber.log.Timber

class GraphCompaniesRepository : GraphCompaniesRepo {

    private val client = Clients.defaultClient

    override suspend fun getCompanies(): Result<GetCompaniesQuery.Data> {
        return try {
            val resp = client.query(GetCompaniesQuery()).execute()
            resp.data?.let {
                Result.success(it)
            } ?: run {
                val errorString = resp.exception?.message.toString()
                Timber.d("Failure $errorString")
                Result.failure(Exception(errorString))
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getCompanyById(companyId: String): Result<GetCompanyByIdQuery.Data> {
        return try {
            val resp = client.query(GetCompanyByIdQuery(companyId)).execute()
            resp.data?.let {
                Result.success(it)
            } ?: Result.failure(Exception(resp.exception?.message.toString()))

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getCompanyByName(companyName: String): Result<GetCompanyByNameQuery.Data> {
        return try {
            val resp = client.query(GetCompanyByNameQuery(companyName)).execute()
            resp.data?.let {
                Result.success(it)
            } ?: Result.failure(Exception(resp.exception?.message.toString()))

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun searchCompaniesWithName(companyName: String): Result<SearchForCompaniesWithNameQuery.Data> {
        return try {
            val resp = client.query(SearchForCompaniesWithNameQuery(companyName)).execute()
            resp.data?.let {
                Result.success(it)
            } ?: Result.failure(Exception(resp.exception?.message.toString()))

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun searchCompaniesWithLocation(locationName: String): Result<SearchForCompaniesWithLocationQuery.Data> {
        return try {
            val resp = client.query(SearchForCompaniesWithLocationQuery(locationName)).execute()
            resp.data?.let {
                Result.success(it)
            } ?: Result.failure(Exception(resp.exception?.message.toString()))

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}