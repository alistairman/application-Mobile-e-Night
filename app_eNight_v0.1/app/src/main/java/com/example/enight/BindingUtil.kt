package com.example.enight.view.profile

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.enight.dataBase.cour.Cour
import com.example.enight.dataBase.profile.Profile

@BindingAdapter("profileEmailText")
fun TextView.profileEmailText(item: Profile) {
    text = item.mail
}

@BindingAdapter("profileFirstnameText")
fun TextView.profileFirstnameText(item: Profile) {
    text = item.firstName
}

@BindingAdapter("profileLastnameText")
fun TextView.profileLastnameText(item: Profile) {
    text = item.lastName
}

@BindingAdapter("courNameTextview")
fun TextView.courNameText(item: Cour) {
    text = item.cour
}

@BindingAdapter("courNbCreditTextview")
fun TextView.courNbCreditText(item: Cour) {
    text = item.nbCredit.toString()
}

@BindingAdapter("courValidedTextview")
fun TextView.courValidedText(item: Cour) {
    text = if(item.valided) "OK"
    else "NOK"
}