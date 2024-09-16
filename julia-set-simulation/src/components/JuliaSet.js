"use client";

import { useEffect, useRef, useState } from "react";

const JuliaSet = () => {
  const canvasRef = useRef(null);
  const [params, setParams] = useState({
    real: -0.7,
    imag: 0.27015,
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

    // Rendering Julia Set
    const drawJuliaSet = () => {
      const imageData = ctx.createImageData(width, height);
      const pixels = imageData.data;

      // Iterate over every pixel
      for (let x = 0; x < width; x++) {
        for (let y = 0; y < height; y++) {
          const zx =
            (x - width / 2) / (0.5 * params.zoom * width) + params.offsetX;
          const zy =
            (y - height / 2) / (0.5 * params.zoom * height) + params.offsetY;

          let i = computeJuliaSet(
            zx,
            zy,
            params.real,
            params.imag,
            params.maxIterations,
          );
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

    const computeJuliaSet = (zx, zy, cx, cy, maxIterations) => {
      let x = zx;
      let y = zy;
      let iteration = 0;

      while (x * x + y * y < 4 && iteration < maxIterations) {
        const xTemp = x * x - y * y + cx;
        y = 2 * x * y + cy;
        x = xTemp;
        iteration++;
      }

      return iteration;
    };

    drawJuliaSet();
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
        width={800}
        height={800}
        onWheel={handleZoom}
        style={{ border: "1px solid black" }}
      ></canvas>
      <div>
        <label>
          Real part of c:
          <input
            type="number"
            step="0.01"
            value={params.real}
            onChange={(e) =>
              setParams({ ...params, real: parseFloat(e.target.value) })
            }
          />
        </label>
        <label>
          Imaginary part of c:
          <input
            type="number"
            step="0.01"
            value={params.imag}
            onChange={(e) =>
              setParams({ ...params, imag: parseFloat(e.target.value) })
            }
          />
        </label>
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

export default JuliaSet;
