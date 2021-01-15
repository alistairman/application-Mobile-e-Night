package com.example.enight

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.enight.dataBase.cour.Cour
import com.example.enight.dataBase.foodTruck.FoodTruck
import com.example.enight.dataBase.profile.Profile

@BindingAdapter("profileEmailText")
fun TextView.profileEmailText(item: Profile) {
    text = item.mail
}

@BindingAdapter("profileFirstNameText")
fun TextView.profileFirstNameText(item: Profile) {
    text = item.firstName
}

@BindingAdapter("profileLastNameText")
fun TextView.profileLastNameText(item: Profile) {
    text = item.lastName
}

@BindingAdapter("courNameTextView")
fun TextView.courNameText(item: Cour?) {
    item?.let { text = item.courId }
}

@BindingAdapter("foodTruckNameTextView")
fun TextView.foodTruckNameText(item: FoodTruck?) {
    item?.let { text = item.location }
}

@BindingAdapter("courDetailTitle")
fun TextView.courDetailTitle(item: Cour) {
    text = item.courId
}

@BindingAdapter("courDetailNbCreditTextView")
fun TextView.courDetailNbCreditText(item: Cour) {
    text = item.nbCredit.toString()
}

@BindingAdapter("courDetailValidedTextView")
fun TextView.courDetailValidedText(item: Cour) {
    text = if(item.valided) "OK"
    else "NOK"
}