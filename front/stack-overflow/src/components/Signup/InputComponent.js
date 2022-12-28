import { useEffect, useState } from "react";
import { userStore } from "../../store/zustandUser";

const InputComponent = ({ CLASS }) => {
  const { setName, setEmail, setPassword } = userStore();
  const [data, setData] = useState("");
  const onchange = (e) => {
    setData(e.target.value);
  };
  useEffect(() => {
    if (CLASS === "text") {
      setName(data);
    } else if (CLASS === "email") {
      setEmail(data);
    } else if (CLASS === "password") {
      setPassword(data);
    }
  }, [data]);
  return (
    <input
      className={CLASS}
      id={CLASS}
      type={CLASS}
      size="30"
      maxLength="100"
      name={CLASS}
      onChange={onchange}
      value={data}
      autoComplete={CLASS === "password" ? "off" : "on"}
    />
  );
};
export default InputComponent;
