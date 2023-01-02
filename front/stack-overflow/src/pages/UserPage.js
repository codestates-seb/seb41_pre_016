import LeftSideBar from "../components/LeftSideBar/LeftSideBar";
import Users from "./Users";

const UserPage = () => {
  return (
    <>
      <LeftSideBar page={"Users"} />
      <Users />
    </>
  );
};

export default UserPage;
