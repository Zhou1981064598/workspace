/*
 * File: demo.c
 *
 * Code generated for Simulink model 'demo'.
 *
 * Model version                  : 1.9
 * Simulink Coder version         : 9.0 (R2018b) 24-May-2018
 * C/C++ source code generated on : Tue Mar 19 14:08:28 2024
 *
 * Target selection: ert.tlc
 * Embedded hardware selection: Intel->x86-64 (Windows64)
 * Code generation objectives: Unspecified
 * Validation result: Not run
 */

#include "demo.h"
#include "demo_private.h"

/* Real-time model */
RT_MODEL_demo_T demo_M_;
RT_MODEL_demo_T *const demo_M = &demo_M_;

/* Model step function */
void demo_step(void)
{
  /* Sum: '<Root>/Sum' incorporates:
   *  Constant: '<Root>/Constant'
   *  Constant: '<Root>/Constant1'
   */
  Demo_Mes_Mp = (uint16_T)((uint32_T)Demo_Add1_C + Demo_Add2_C);
}

/* Model initialize function */
void demo_initialize(void)
{
  /* Registration code */

  /* initialize error status */
  rtmSetErrorStatus(demo_M, (NULL));

  /* block I/O */

  /* custom signals */
  Demo_Mes_Mp = 0U;
}

/* Model terminate function */
void demo_terminate(void)
{
  /* (no terminate code required) */
}

/*
 * File trailer for generated code.
 *
 * [EOF]
 */
