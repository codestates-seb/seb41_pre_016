import LeftSideBar from "../components/LeftSideBar/LeftSideBar";
import useFetch from "../store/useFetch";
const MainPage = () => {
  const [data, isPending, error] = useFetch("/user/1");
  return (
    <>
      <LeftSideBar />
      {error && <div>error</div>}
      {isPending && <div>Loading</div>}
      {data && console.log("HI")}
      <a href="/questions/ask">
        <button>Ask question</button>
      </a>
    </>
  );
};

export default MainPage;
