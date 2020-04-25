package br.com.caelum.livraria.tx;

import java.io.Serializable;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Log
@Interceptor
public class GerenciadorDeLog implements Serializable {

	private static final long serialVersionUID = 1L;

	@AroundInvoke
	public Object executaLog(InvocationContext contexto) throws Exception {
				
		long antes = System.currentTimeMillis();
		
		String nomeMetodo = contexto.getMethod().getName();
		Object proceder = contexto.proceed();
		
		// pegando tempo estimado após proceder o método
		long depois = System.currentTimeMillis();
		long tempoExecutado = depois - antes;
		
		System.out.println("O método " + nomeMetodo + "() gastou " + tempoExecutado + " para ser executado!");
		
		return proceder;
	}
}
