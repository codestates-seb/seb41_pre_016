import LeftSideBar from "../components/LeftSideBar/LeftSideBar";
import {fetchStore} from "../store/zustandFetch";
import {useEffect, useState} from "react";
import {useCookies} from "react-cookie";
import axios from "axios";
const MainPage = () => {
    const{data,isLoading,error,fetch}=fetchStore()
    useEffect(()=>{fetch("/user/1")},[])

    const [cookies, setCookie, removeCookie] = useCookies(['access_jwt']);

    useEffect(() => {
        if(cookies.access_jwt !== undefined){
            console.log(cookies.access_jwt)
        }
    },[]);

  return (
    <>
      <LeftSideBar />
      {error && <div>error</div>}
      {isLoading && <div>Loading</div>}
      {data && console.log(data)}
      <a href="/questions/ask">
        <button>Ask question</button>
      </a>
    </>
  );
};

export default MainPage;
