import logo from './logo.svg';
import React, {useState, useEffect} from "react;"
import './App.css';
import axios from "axios";

const UserProfiles=()=>{
  const fetchUserProfiles()=>{
    axios.get("http://localhost:8080").then(res=>{
      console.log(res);
    });
  }
  useEffect(()=>{
    fetchUserProfiles();
  ,[]})
}


function App() {
  return (
    
  );
}

export default App;
