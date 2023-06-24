const container = document.createElement("div");
      Object.assign(container.style, {
        position: "fixed",
        width: "100vw",
        height: "100vh",
        backgroundColor: "rgba(0,0,0,0.1",
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        top: 0,
        left: 0,
        zIndex: 100,
      });
      const loaderParent = document.createElement("div");
      const loader = document.createElement("div");
      loaderParent.classList.add("spinner");
      loader.classList.add("spinner__gradient");
      loaderParent.appendChild(loader);
      container.appendChild(loaderParent);
      document.body.appendChild(container);

      let colorArray = [`#168aad`, `#184e77`];
      let start1 = `conic-gradient(transparent 0deg 0deg, ${colorArray[1]} 1deg 90deg, ${colorArray[0]} 270deg 360deg)`;
      let end1 = `conic-gradient(transparent 1deg 360deg, ${colorArray[1]} 1deg 90deg, ${colorArray[0]} 270deg 360deg)`;
      let start2 = `conic-gradient(${colorArray[1]} 0deg 0deg, transparent 1deg 360deg)`;
      let end2 = `conic-gradient(${colorArray[1]} 1deg 90deg, ${colorArray[0]} 270deg 360deg, transparent 359deg 360deg)`;

      let tl = gsap.timeline({ repeat: -1 });

      tl.fromTo(
        ".spinner__gradient",
        { background: start1 },
        { ease: "sine.out", rotation: 360, duration: 1.5, background: end1 }
      ).fromTo(
        ".spinner__gradient",
        { background: start2 },
        {
          ease: "sine.out",
          rotation: 720, // continuation after first 360deg rotation
          duration: 1.5,
          background: end2,
        }
      );