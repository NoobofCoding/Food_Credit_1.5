package com.example.foodcredit15.data

import com.example.foodcredit15.network.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VendingMachineRepo {
    private val api = RetrofitClient.instance.create(ApiService::class.java)

    fun createMachine(machine: VendingMachineRequest, onResult: (VendingMachineResponse?) -> Unit) {
        api.createMachine(machine).enqueue(object : Callback<VendingMachineResponse> {
            override fun onResponse(call: Call<VendingMachineResponse>, response: Response<VendingMachineResponse>) {
                onResult(response.body())
            }
            override fun onFailure(call: Call<VendingMachineResponse>, t: Throwable) {
                onResult(null)
            }
        })
    }

    fun getMachines(onResult: (List<VendingMachineResponse>?) -> Unit) {
        api.getMachines().enqueue(object : Callback<List<VendingMachineResponse>> {
            override fun onResponse(call: Call<List<VendingMachineResponse>>, response: Response<List<VendingMachineResponse>>) {
                onResult(response.body())
            }
            override fun onFailure(call: Call<List<VendingMachineResponse>>, t: Throwable) {
                onResult(null)
            }
        })
    }

    fun updateMachine(id: Int, machine: VendingMachineRequest, onResult: (Boolean) -> Unit) {
        api.updateMachine(id, machine).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                onResult(response.isSuccessful)
            }
            override fun onFailure(call: Call<Void>, t: Throwable) {
                onResult(false)
            }
        })
    }

    fun deleteMachine(id: Int, onResult: (Boolean) -> Unit) {
        api.deleteMachine(id).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                onResult(response.isSuccessful)
            }
            override fun onFailure(call: Call<Void>, t: Throwable) {
                onResult(false)
            }
        })
    }
}
