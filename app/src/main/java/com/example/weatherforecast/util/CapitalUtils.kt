package com.example.weatherforecast.util

import com.example.weatherforecast.model.CapitalModel

class CapitalUtils {
    fun getCapitalsData(): ArrayList<CapitalModel> {
        val listCapitals: ArrayList<CapitalModel> = ArrayList()

        listCapitals.add(CapitalModel("Aracaju", -10.905666940509276, -37.06826852961524))
        listCapitals.add(CapitalModel("Belém", -1.457948802941501, -48.480057080322105))
        listCapitals.add(CapitalModel("Belo Horizonte", -19.923299312532013, -43.95514524303994))
        listCapitals.add(CapitalModel("Boa Vista", 2.823621506691642, -60.69188882055031))
        listCapitals.add(CapitalModel("Brasília", -15.795714037310765, -47.870088689012704))
        listCapitals.add(CapitalModel("Campo Grande", -20.48102205646418, -54.63349550373323))
        listCapitals.add(CapitalModel("Cuiabá", -15.577970855802432, -56.08646106667824))
        listCapitals.add(CapitalModel("Curitiba", -25.440471292138643, -49.28775874387652))
        listCapitals.add(CapitalModel("Florianópolis", -27.57303761297585, -48.48498832186464))
        listCapitals.add(CapitalModel("Fortaleza", -3.756950785740916, -38.556827610126284))
        listCapitals.add(CapitalModel("Goiânia", -16.66884306483365, -49.331755133797294))
        listCapitals.add(CapitalModel("João Pessoa", -7.152392630268555, -34.875080082510905))
        listCapitals.add(CapitalModel("Macapá", 0.15189692361676368, -51.08795758669697))
        listCapitals.add(CapitalModel("Maceió", -9.510880846543953, -35.72352328088551))
        listCapitals.add(CapitalModel("Manaus", -3.041906720174809, -60.0103857546854))
        listCapitals.add(CapitalModel("Natal", -5.7937468614138465, -35.19342809123155))
        listCapitals.add(CapitalModel("Palmas", -10.257991101292937, -48.33695737787952))
        listCapitals.add(CapitalModel("Porto Alegre", -30.083221860133243, -51.184027904149296))
        listCapitals.add(CapitalModel("Porto Velho", -9.336666342792691, -64.1897614836496))
        listCapitals.add(CapitalModel("Recife", -8.057098026308145, -34.88568674140311))
        listCapitals.add(CapitalModel("Rio Branco", -9.965018090461118, -67.84037202272772))
        listCapitals.add(CapitalModel("Rio de Janeiro", -22.915055367420504, -43.24419266197062))
        listCapitals.add(CapitalModel("Salvador", -12.981801609111836, -38.49152106052599))
        listCapitals.add(CapitalModel("São Luís", -2.53147591786489, -44.28799685289086))
        listCapitals.add(CapitalModel("São Paulo", -23.553720783103095, -46.60774261130093))
        listCapitals.add(CapitalModel("Vitória", -20.321227269824533, -40.34213324389549))
        listCapitals.add(CapitalModel("Teresina", -5.095770386304058, -42.798779462629085))

        return listCapitals
    }
}