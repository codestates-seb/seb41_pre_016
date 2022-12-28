import LeftSideBar from "../components/LeftSideBar/LeftSideBar";
import {fetchStore} from "../store/zustandFetch";
import {useEffect} from "react";
const MainPage = () => {
    const{data,isLoading,error,fetch}=fetchStore()
    useEffect(()=>{fetch("/user/1")},[])
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
