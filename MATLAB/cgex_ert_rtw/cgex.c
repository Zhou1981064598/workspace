/*
 * File: cgex.c
 *
 * Code generated for Simulink model 'cgex'.
 *
 * Model version                  : 1.6
 * Simulink Coder version         : 9.0 (R2018b) 24-May-2018
 * C/C++ source code generated on : Thu Feb 29 14:03:21 2024
 *
 * Target selection: ert.tlc
 * Embedded hardware selection: Intel->x86-64 (Windows64)
 * Code generation objectives: Unspecified
 * Validation result: Not run
 */

#include "cgex.h"
#include "cgex_private.h"

/* Exported block parameters */
real_T k = 2.0;                        /* Variable: k
                                        * Referenced by: '<Root>/Gain'
                                        */

/* External inputs (root inport signals with default storage) */
ExtU_cgex_T cgex_U;

/* External outputs (root outports fed by signals with default storage) */
ExtY_cgex_T cgex_Y;

/* Real-time model */
RT_MODEL_cgex_T cgex_M_;
RT_MODEL_cgex_T *const cgex_M = &cgex_M_;

/* Model step function */
void cgex_step(void)
{
  /* Outport: '<Root>/Out1' incorporates:
   *  Gain: '<Root>/Gain'
   *  Inport: '<Root>/Input'
   *  Inport: '<Root>/Input1'
   *  Sum: '<Root>/Sum'
   */
  cgex_Y.Out1 = (cgex_U.x + cgex_U.y) * k;
}

/* Model initialize function */
void cgex_initialize(void)
{
  /* Registration code */

  /* initialize error status */
  rtmSetErrorStatus(cgex_M, (NULL));

  /* external inputs */
  (void)memset(&cgex_U, 0, sizeof(ExtU_cgex_T));

  /* external outputs */
  cgex_Y.Out1 = 0.0;
}

/* Model terminate function */
void cgex_terminate(void)
{
  /* (no terminate code required) */
}

/*
 * File trailer for generated code.
 *
 * [EOF]
 */
