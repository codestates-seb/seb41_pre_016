import { useEffect, useRef, useState } from "react";

const DropdownCustomHook = (initialState) => {
  const [dropdown, setDropdown] = useState(initialState);
  const ref = useRef(null);

  const removeHandler = () => {
    setDropdown(!dropdown);
  };

  useEffect(() => {
    const onClick = (e) => {
      if (ref.current !== null || !ref.current.contain(e.target)) {
        setDropdown(false);
      }
    };
    if (dropdown) {
      window.addEventListener("mousedown", onClick);
    }
    return () => {
      window.removeEventListener("mousedown", onClick);
    };
  }, [dropdown, ref]);
  return [dropdown, ref, removeHandler];
};
export default DropdownCustomHook;
