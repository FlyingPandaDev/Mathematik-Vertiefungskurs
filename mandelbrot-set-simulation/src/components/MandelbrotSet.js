"use client"; // Ensure this is treated as a Client Component

import { useEffect, useRef, useState } from "react";

const MandelbrotSet = () => {
  const canvasRef = useRef(null);
  const [params, setParams] = useState({
    maxIterations: 300,
    zoom: 1,
    offsetX: 0,
    offsetY: 0,
  });

  useEffect(() => {
    const canvas = canvasRef.current;
    const ctx = canvas.getContext("2d");
    const width = canvas.width;
    const height = canvas.height;

    // Rendering Mandelbrot Set
    const drawMandelbrotSet = () => {
      const imageData = ctx.createImageData(width, height);
      const pixels = imageData.data;

      for (let x = 0; x < width; x++) {
        for (let y = 0; y < height; y++) {
          // Convert pixel coordinate to complex number (c = x + iy)
          const c_re =
            (x - width / 2) / (0.5 * params.zoom * width) + params.offsetX;
          const c_im =
            (y - height / 2) / (0.5 * params.zoom * height) + params.offsetY;

          let i = computeMandelbrotSet(c_re, c_im, params.maxIterations);
          const pixelIndex = (y * width + x) * 4;

          // Color based on number of iterations
          const color =
            i === params.maxIterations ? 0 : (i * 255) / params.maxIterations;
          pixels[pixelIndex] = color; // R
          pixels[pixelIndex + 1] = color; // G
          pixels[pixelIndex + 2] = color; // B
          pixels[pixelIndex + 3] = 255; // A (fully opaque)
        }
      }

      // Put pixel data onto the canvas
      ctx.putImageData(imageData, 0, 0);
    };

    const computeMandelbrotSet = (c_re, c_im, maxIterations) => {
      let z_re = 0,
        z_im = 0;
      let iteration = 0;

      // Iterate z = z^2 + c until |z| > 2 or maximum iterations are reached
      while (z_re * z_re + z_im * z_im <= 4 && iteration < maxIterations) {
        const z_re_temp = z_re * z_re - z_im * z_im + c_re;
        z_im = 2 * z_re * z_im + c_im;
        z_re = z_re_temp;
        iteration++;
      }

      return iteration;
    };

    drawMandelbrotSet();
  }, [params]);

  const handleZoom = (e) => {
    setParams((prevParams) => ({
      ...prevParams,
      zoom: prevParams.zoom * (e.deltaY > 0 ? 1.1 : 0.9),
    }));
  };

  return (
    <div>
      <canvas
        ref={canvasRef}
        width={1000}
        height={1000}
        onWheel={handleZoom}
        style={{ border: "1px solid black" }}
      ></canvas>
      <div>
        <label>
          Max Iterations:
          <input
            type="number"
            value={params.maxIterations}
            onChange={(e) =>
              setParams({
                ...params,
                maxIterations: parseInt(e.target.value, 10),
              })
            }
          />
        </label>
      </div>
    </div>
  );
};

export default MandelbrotSet;
